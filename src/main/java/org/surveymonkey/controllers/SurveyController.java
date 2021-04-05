package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.surveymonkey.kafka.Producer;
import org.surveymonkey.kafka.Message;
import org.surveymonkey.models.*;
import org.surveymonkey.services.iservices.IEndUserService;
import org.surveymonkey.services.iservices.IQuestionService;
import org.surveymonkey.services.iservices.ISurveyService;


import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class SurveyController {

    @Autowired
    private ISurveyService surveyService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IEndUserService endUserService;

    @Autowired
    private Producer producer;


    private final String TOPIC = "Survey";

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/survey/{surveyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey getSurvey(@PathVariable long surveyID) {
        return surveyService.findById(surveyID);
    }

    @GetMapping(value = "/survey/{surveyID}")
    public String getSurvey(@PathVariable long surveyID, Model model) {
        model.addAttribute("survey", surveyService.findById(surveyID));

        List<Question> questionList = surveyService.findById(surveyID).getQuestions();
        ArrayList<TextQuestion> textQuestionList = new ArrayList<>();
        ArrayList<NumberQuestion> numberQuestionList = new ArrayList<>();
        ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<>();

        for (Question question : questionList) {
            Question.QuestionType type = question.getType();
            if (type.compareTo(Question.QuestionType.TEXT) == 0) {
                textQuestionList.add((TextQuestion) question);
            } else if (type.compareTo(Question.QuestionType.NUMBER) == 0) {
                numberQuestionList.add((NumberQuestion) question);
            } else if (type.compareTo(Question.QuestionType.CHOICE) == 0) {
                choiceQuestionList.add((ChoiceQuestion) question);
            }
        }

        model.addAttribute("textQuestions", textQuestionList);
        model.addAttribute("numberQuestions", numberQuestionList);
        model.addAttribute("choiceQuestions", choiceQuestionList);

        return "doSurvey";
    }

    @GetMapping(value = "/survey/{surveyID}/fill")
    public String fillSurvey(@PathVariable long surveyID, Model model) {
        Survey s = surveyService.findById(surveyID);
        if(s.isClosed()) {
            // Don't add response if Survey is closed
            return "redirect:/survey/" + surveyID;
        }
        model.addAttribute("survey", s);

        List<Question> questionList = s.getQuestions();
        ArrayList<TextQuestion> textQuestionList = new ArrayList<>();
        ArrayList<NumberQuestion> numberQuestionList = new ArrayList<>();
        ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<>();

        for (Question question : questionList) {
            Question.QuestionType type = question.getType();
            if (type.compareTo(Question.QuestionType.TEXT) == 0) {
                textQuestionList.add((TextQuestion) question);
            } else if (type.compareTo(Question.QuestionType.NUMBER) == 0) {
                numberQuestionList.add((NumberQuestion) question);
            } else if (type.compareTo(Question.QuestionType.CHOICE) == 0) {
                choiceQuestionList.add((ChoiceQuestion) question);
            }
        }

        model.addAttribute("textQuestions", textQuestionList);
        model.addAttribute("numberQuestions", numberQuestionList);
        model.addAttribute("choiceQuestions", choiceQuestionList);

        return "fillSurvey";
    }

    @PostMapping(value = "/survey/{surveyID}/fill", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String fillSurveyComplete(@RequestBody MultiValueMap<String, String> formData, @PathVariable long surveyID) {
        Survey s = surveyService.findById(surveyID);
        if(s.isClosed()) {
            // Don't add response if Survey is closed
            return "redirect:/survey/" + surveyID;
        }
        for (Map.Entry formElement : formData.entrySet()) {
            String formKey = (String) formElement.getKey();
            String formValue = (String) ((LinkedList) formElement.getValue()).getFirst();

            Question q = questionService.findById(Integer.parseInt(formKey));
            q.setAnswer(formValue);
            questionService.save(q);

            String message = "Adding answer to survey: " + surveyID + " for question: " + q.getId();
            producer.send(TOPIC,new Message(0, message));
        }
        return "redirect:/survey/" + surveyID;
    }

    @GetMapping("/survey")
    public String viewSurvey(@RequestParam long surveyID) {
        return "redirect:/survey/" + surveyID;
    }

    @PostMapping(value = "/survey", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey postSurvey() {
        Survey survey = new Survey();
        surveyService.save(survey);

        String message = "Creating a new survey";
        producer.send(TOPIC,new Message(0, message));

        return survey;
    }

    @PostMapping(value = "/{userID}/survey")
    public String postSurvey(Model model, @PathVariable long userID) {
        Survey survey = new Survey();
        EndUser user = endUserService.findById(userID);
        surveyService.save(survey);
        user.addSurvey(survey);
        endUserService.save(user);
        model.addAttribute("survey", survey);


        String message = "Creating a new survey for user: " + userID;
        producer.send(TOPIC,new Message(0, message));

        return "surveyCreated";
    }

    @PostMapping(value = "/survey/{surveyID}/createQuestion")
    public ModelAndView addQuestion(HttpServletRequest request, @PathVariable long surveyID, @RequestParam String questionType) {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:/survey/" + surveyID + "/" + questionType);
    }

    @GetMapping(value = "/survey/{surveyID}/createQuestion")
    public String addQuestion(@PathVariable long surveyID, Model model) {
        model.addAttribute("survey", surveyService.findById(surveyID));
        return "createQuestion";
    }

    /**
     * View the responses of a survey
     * @param surveyID Survey to view responses of
     * @param model
     * @return Renders a view containing survey responses
     */
    @GetMapping(value = "/survey/{surveyID}/responses")
    public String surveyResponses(@PathVariable long surveyID, Model model) {
        Survey survey = surveyService.findById(surveyID);
        List<Question> questionList = survey.getQuestions();
        model.addAttribute("survey", survey);


        HashMap<Integer, ArrayList<Integer>>  questionHistogramData = new HashMap<Integer, ArrayList<Integer>>();

        for(int i = 0; i < questionList.size(); i++){
            Question question = questionList.get(i);
            if(question instanceof NumberQuestion) {
                NumberQuestion numberQuestion = (NumberQuestion) question;
                List<String> answers = numberQuestion.getAnswers();
                ArrayList<Integer> dataSet = new ArrayList<Integer>();
                for (int j = 0; j < answers.size(); j++) {
                    if(!answers.get(j).equals("")) {
                        dataSet.add(Integer.parseInt(answers.get(j)));
                    }
                }
                questionHistogramData.put((int) question.getId(), dataSet);
            }else if(question instanceof ChoiceQuestion){
                ChoiceQuestion choiceQuestion = (ChoiceQuestion) question;
                List<String> answers = choiceQuestion.getAnswers();
                List<String> options = choiceQuestion.getChoices();
                ArrayList<String> choices = new ArrayList<String>();
                ArrayList<Integer> occurences = new ArrayList<Integer>();
                for (int j = 0; j < options.size(); j++) {

                    String option = (options.get(j));
                    int occurrence = Collections.frequency(answers, option);
                    occurences.add(occurrence);

                }
                questionHistogramData.put((int) question.getId(), occurences);
            }

        }


        model.addAttribute("questionHistogramData", questionHistogramData);
        model.addAttribute("questions", questionList);
        return "viewResponses";
    }

    /**
     * This action allows a user to close a Survey
     * @param surveyID Survey to close
     * @return Redirect user to main Survey view page
     */
    @PostMapping(value = "/survey/{surveyID}/close")
    public String closeQuestion(@PathVariable long surveyID) {
        Survey survey = surveyService.findById(surveyID);
        survey.markAsClosed();
        surveyService.save(survey);

        String message = "Closing survey: " + surveyID;
        producer.send(TOPIC,new Message(0, message));

        return "redirect:/survey/" + surveyID;
    }

    @GetMapping(value = "/SurveyControllerController/test")
    @ResponseBody
    public String testSurveyController() {
        return "SurveyController is working";
    }

    @GetMapping(value="survey/answer")
    public String selectSurveyToAnswer(Model model){
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : surveyService.findAll()) {
            if(!survey.isClosed()){
                surveys.add(survey);
            }
        }
        model.addAttribute("surveys", surveys);
        return "displayAllOpenSurveys";
    }
}