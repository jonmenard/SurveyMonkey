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
public class SurveyController extends ApplicationController {

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
    public String home(@CookieValue(value = "user_id", defaultValue = "-1") String user_id, Model model) {
        EndUser user = endUserService.findById(Long.parseLong(user_id));
        if(user != null) {
            model.addAttribute("user", user);
            return "userManagement";
        }
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
        return "doSurvey";
    }

    @GetMapping(value = "/survey/{surveyID}/fill")
    public String fillSurvey(@PathVariable long surveyID, Model model) {
        Survey s = surveyService.findById(surveyID);
        if(s == null) return "error";
        if(s.isClosed()) {
            // Don't add response if Survey is closed
            return "redirect:/survey/" + surveyID;
        }
        model.addAttribute("survey", s);

        List<Question> questionList = s.getQuestions();


        model.addAttribute("questions", questionList);
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
            sendMessage(message);
        }
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

        String message = "Creating a new survey";
        sendMessage(message);

        return survey;
    }

    @GetMapping(value = "/{userID}/survey")
    public String createSurvey(Model model, @PathVariable long userID) {
        model.addAttribute("userID", userID);
        return "createSurvey";
    }


    @PostMapping(value = "/survey/createNew")
    public String createNewSurvey(Model model, @RequestParam long userID, @RequestParam String surveyName, @RequestParam String surveyDescription) {

        Survey survey = new Survey(userID, surveyName, surveyDescription);
        surveyService.save(survey);
        model.addAttribute("survey", survey);

       String message = "Creating a new survey for user: " + userID;
       sendMessage(message);

       return "surveyCreated";
    }



    @PostMapping(value = "/survey/{surveyID}/createQuestion")
    public ModelAndView addQuestion(HttpServletRequest request, @PathVariable long surveyID, @RequestParam String questionType) {
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        String message = "Adding " + questionType + " question to survey: " + surveyID;
        sendMessage(message);
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
        model.addAttribute("questionHistogramData", surveyService.getSurveyStatistics((int) surveyID));
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
        sendMessage(message);

        return  "redirect:/survey/" + surveyID + "/" + survey.getEndUserId();
    }

    @GetMapping(value = "/SurveyControllerController/test")
    @ResponseBody
    public String testSurveyController() {
        return "SurveyController is working";
    }

    @GetMapping(value="survey/answer/{userID}")
    public String selectSurveyToAnswer(@PathVariable long userID, Model model){
        List<Survey> surveys = new ArrayList<>();
        for (Survey survey : surveyService.findAll()) {
            if(!survey.isClosed()){
                surveys.add(survey);
            }
        }
        model.addAttribute("surveys", surveys);
        model.addAttribute("user", userID);
        return "displayAllOpenSurveys";
    }


    @GetMapping(value = "/survey/{userId}/allsurveys")
    public String displayAllUsersSurveys(Model model, @PathVariable long userId) {

        // Find surveys by userid
        List<Survey> surveys = surveyService.findSurveysByUserId((long) userId);

        model.addAttribute("userId", userId);
        model.addAttribute("surveys", surveys);
        String message = "User " + userId + " accessing open survey list";
        sendMessage(message);
        return "displayUserSurveys";
    }



    @PostMapping(value = "/survey/{surveyId}/{userID}/swapQuestion")
    public String swapQuestionOrder(Model model, @PathVariable long surveyId, @PathVariable long userID, @RequestParam int selectedQuestion, @RequestParam String submit){

        if(submit.equals("EditBounds")){
            return "redirect:/survey/" +  surveyId + "/numberquestion/" + selectedQuestion +"/bounds";
        }else if(submit.equals("EditChoices")){
            return "redirect:/survey/" +  surveyId + "/choicequestion/" + selectedQuestion +"/choices";
        }


        surveyService.swapQuestion((int) surveyId,selectedQuestion,submit);
        return "redirect:/survey/" + surveyId + "/" + userID;

    }



    public void sendMessage(String message){
        producer.send(TOPIC, new Message(0, message));
    }
}