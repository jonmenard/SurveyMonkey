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
import org.surveymonkey.kafka.KafkaAnalytics;
import org.surveymonkey.kafka.Producer;
import org.surveymonkey.kafka.Message;
import org.surveymonkey.models.*;
import org.surveymonkey.services.iservices.IEndUserService;
import org.surveymonkey.services.iservices.IKafkaAnalyticsService;
import org.surveymonkey.services.iservices.IQuestionService;
import org.surveymonkey.services.iservices.ISurveyService;


import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class SurveyController extends ApplicationController {

    @Autowired
    private ISurveyService surveyService;

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private IEndUserService endUserService;

    @Autowired
    private Producer producer;

    @Autowired
    private IKafkaAnalyticsService kafkaAnalyticsService;


    private final String TOPIC = "Survey";

    @RequestMapping("/")
    public String home(@CookieValue(value = "user_id", defaultValue = "-1") String user_id, Model model) {
        EndUser user = endUserService.findById(Long.parseLong(user_id));
        sendMessage("updatesAndInserts","select");
        if(user != null) {
            model.addAttribute("user", user);
            sendMessage("PageVisited","userManagement");
            return "userManagement";

        }
        sendMessage("PageVisited","index");
        return "index";
    }

    @GetMapping(value = "/survey/{surveyID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey getSurvey(@PathVariable long surveyID) {
        return surveyService.findById(surveyID);
    }

    @GetMapping(value = "/survey/{surveyID}/{userID}")
    public String getSurvey(@PathVariable long surveyID, @PathVariable long userID, Model model) {
        model.addAttribute("survey", surveyService.findById(surveyID));
        model.addAttribute("userID", userID);
        List<Question> questionList = surveyService.findById(surveyID).getQuestions();
        model.addAttribute("questions", questionList);
        sendMessage("updatesAndInserts","select");
        sendMessage("PageVisited","doSurvey");
        return "doSurvey";
    }

    @GetMapping(value = "/survey/{surveyID}/fill")
    public String fillSurvey(@PathVariable long surveyID, Model model) {
        Survey s = surveyService.findById(surveyID);
        sendMessage("updatesAndInserts","select");
        if(s == null) return "error";
        if(s.isClosed()) {
            // Don't add response if Survey is closed
            return "redirect:/survey/" + surveyID;
        }
        model.addAttribute("survey", s);

        List<Question> questionList = s.getQuestions();


        model.addAttribute("questions", questionList);
        sendMessage("PageVisited","fillSurvey");
        return "fillSurvey";
    }

    @PostMapping(value = "/survey/{surveyID}/fill", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String fillSurveyComplete(@RequestBody MultiValueMap<String, String> formData, @PathVariable long surveyID) {
        Survey s = surveyService.findById(surveyID);
        if(s.isClosed()) {
            // Don't add response if Survey is closed
            return  "redirect:/survey/" + surveyID;
        }
        for (Map.Entry formElement : formData.entrySet()) {
            String formKey = (String) formElement.getKey();
            String formValue = (String) ((LinkedList) formElement.getValue()).getFirst();

            Question q = questionService.findById(Integer.parseInt(formKey));
            q.setAnswer(formValue);
            questionService.save(q);

            String message = "Adding answer to survey: " + surveyID + " for question: " + q.getId();
            sendMessage("Survey",message);
        }
        sendMessage("updatesAndInserts","update");
        sendMessage("PageVisited","responseThankYou");
        sendMessage("SurveyInformation","answered");
        return  "responseThankYou";
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
        sendMessage("updatesAndInserts","update");
        String message = "Creating a new survey";
        sendMessage("Survey",message);

        return survey;
    }

    @GetMapping(value = "/user/{userID}/survey")
    public String createSurvey(Model model, @PathVariable long userID) {
        model.addAttribute("userID", userID);
        sendMessage("PageVisited","createSurvey");
        return "createSurvey";
    }


    @PostMapping(value = "/survey/createNew")
    public String createNewSurvey(Model model, @RequestParam long userID, @RequestParam String surveyName, @RequestParam String surveyDescription) {
        Survey survey = new Survey(endUserService.findById(userID), surveyName, surveyDescription);
        surveyService.save(survey);

        model.addAttribute("survey", survey);

       String message = "Creating a new survey for user: " + userID;
        sendMessage("Survey",message);
        sendMessage("updatesAndInserts","update");
        sendMessage("PageVisited","surveyCreated");
        sendMessage("SurveyInformation","created");
       return "surveyCreated";
    }



    @PostMapping(value = "/survey/{surveyID}/createQuestion")
    public ModelAndView addQuestion(HttpServletRequest request, @PathVariable long surveyID, @RequestParam String questionType) {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        String message = "Adding " + questionType + " question to survey: " + surveyID;
        sendMessage("Survey",message);
        return new ModelAndView("redirect:/survey/" + surveyID + "/" + questionType);
    }

    @GetMapping(value = "/survey/{surveyID}/createQuestion")
    public String addQuestion(@PathVariable long surveyID, Model model) {
        model.addAttribute("survey", surveyService.findById(surveyID));
        sendMessage("updatesAndInserts","select");
        sendMessage("PageVisited","createQuestion");
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
        sendMessage("updatesAndInserts","select");
        List<Question> questionList = survey.getQuestions();
        model.addAttribute("survey", survey);
        model.addAttribute("questionHistogramData", surveyService.getSurveyStatistics((int) surveyID));
        model.addAttribute("questions", questionList);
        sendMessage("PageVisited","viewResponses");
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
        sendMessage("updatesAndInserts","update");

        String message = "Closing survey: " + surveyID;
        sendMessage("Survey",message);

        return  "redirect:/survey/" + surveyID + "/" + survey.getEndUserId();
    }

    @GetMapping(value = "/SurveyControllerController/test")
    @ResponseBody
    public String testSurveyController() {
        return "SurveyController is working";
    }

    @GetMapping(value="all-surveys")
    public String selectSurveyToAnswer(Model model){
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : surveyService.findAll()) {
            if(!survey.isClosed()){
                surveys.add(survey);
            }
        }
        sendMessage("updatesAndInserts","select");
        model.addAttribute("surveys", surveys);
        sendMessage("PageVisited","displayAllOpenSurveys");
        return "displayAllOpenSurveys";
    }


    @GetMapping(value = "/user/{userId}/surveys")
    public String displayAllUsersSurveys(Model model, @PathVariable long userId) {

        // Find surveys by userid
        List<Survey> surveys = surveyService.findSurveysByUser(endUserService.findById(userId));
        sendMessage("updatesAndInserts","select");
        sendMessage("PageVisited","displayAllOpenSurveys");

        model.addAttribute("userId", userId);
        model.addAttribute("surveys", surveys);
        String message = "User " + userId + " accessing open survey list";
        sendMessage("Survey",message);
        sendMessage("PageVisited","displayUserSurveys");
        return "displayUserSurveys";
    }



    @PostMapping(value = "/survey/{surveyId}/{userID}/swapQuestion")
    public String swapQuestionOrder(Model model, @PathVariable long surveyId, @PathVariable long userID, @RequestParam int selectedQuestion, @RequestParam String submit){

        if(submit.equals("EditBounds")){
            String message = "Editing bounds of Question " + selectedQuestion + " from Survey " + surveyId;
            sendMessage("Survey",message);
            return "redirect:/survey/" +  surveyId + "/numberquestion/" + selectedQuestion +"/bounds";
        }else if(submit.equals("EditChoices")){
            String message = "Editing choices of Question " + selectedQuestion + " from Survey " + surveyId;
            sendMessage("Survey",message);
            return "redirect:/survey/" +  surveyId + "/choicequestion/" + selectedQuestion +"/choices";
        }
        else if (submit.equals("Up")){
            String message = "Moving Question " + selectedQuestion + " from Survey " + surveyId + " " + submit;
            sendMessage("Survey",message);
            surveyService.swapQuestion((int) surveyId,selectedQuestion,"Up");
        }else if (submit.equals("Down")){
            String message = "Moving Question " + selectedQuestion + " from Survey " + surveyId + " " + submit;
            sendMessage("Survey",message);
            surveyService.swapQuestion((int) surveyId,selectedQuestion,"Down");
        }
        sendMessage("updatesAndInserts","update");

        return "redirect:/survey/" + surveyId + "/" + userID;

    }

    @GetMapping("/project/admin/kafka/analytics")
    public String adminKafkaAnalytics(Model model) {
        KafkaAnalytics kafkaAnalytics =  kafkaAnalyticsService.findById(1349);
        ArrayList<Long> templateVisits = new ArrayList<Long>(Arrays.asList(kafkaAnalytics.getAddQuestionChoiceTemplateVisited(), kafkaAnalytics.getChangeQuestionBoundsTemplateVisited(), kafkaAnalytics.getCreatQuestionTemplateVisited(), kafkaAnalytics.getCreateSurveyTemplateVisited(), kafkaAnalytics.getCreateUserTemplateVisited(),
                kafkaAnalytics.getDisplayAllOpenSurveysTemplateVisited(), kafkaAnalytics.getDisplayUserSurveysTemplateVisited(), kafkaAnalytics.getDoSurveyTemplateVisited(), kafkaAnalytics.getErrorTemplateVisited(), kafkaAnalytics.getFillSurveyTemplateVisited(),
                kafkaAnalytics.getIndexTemplateVisited(), kafkaAnalytics.getResponseThankYouTemplateVisited(), kafkaAnalytics.getSurveyCreated(), kafkaAnalytics.getViewResponsesTemplateVisited()));
        Long selects = kafkaAnalytics.getDatabaseSelect();
        Long updates = kafkaAnalytics.getDatabaseUpdate();
        Long surveyCreated = kafkaAnalytics.getSurveyCreated();
        Long surveyAnswered = kafkaAnalytics.getSurveyAnswered();

        model.addAttribute("templateVisits",templateVisits);
        model.addAttribute("selects",selects);
        model.addAttribute("updates",updates);
        model.addAttribute("surveyCreated",surveyCreated);
        model.addAttribute("surveyAnswered",surveyAnswered);

        return "adminKafkaAnalytics";
    }

    public void sendMessage(String topic, String message){
        producer.send(topic, new Message(0, message));
    }

}