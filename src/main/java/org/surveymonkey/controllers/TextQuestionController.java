package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;
import org.surveymonkey.models.TextQuestion;
import org.surveymonkey.services.iservices.IQuestionService;
import org.surveymonkey.services.iservices.ISurveyService;

@Controller
public class TextQuestionController extends ApplicationController {

    @Autowired
    private ISurveyService surveyService;

    @Autowired
    private IQuestionService questionService;



    private final String TOPIC = "Question";

    @PostMapping(value = "/survey/{surveyID}/textquestion", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey postTextQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyService.findById(surveyID);
        survey.addQuestion(new TextQuestion(question));
        surveyService.save(survey);




        return survey;
    }

    @PostMapping(value = "/survey/{surveyID}/textquestion")
    public String createTextQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyService.findById(surveyID);
        survey.addQuestion(new TextQuestion(question));
        surveyService.save(survey);

        return "redirect:/survey/" + surveyID + "/" + survey.getEndUserId();

    }

    @DeleteMapping(value = "/survey/{surveyID}/textquestion", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey deleteTextQuestion(@PathVariable long surveyID, @RequestParam long textQuestionID) {
        Survey survey = surveyService.findById(surveyID);
        Question textQuestion = questionService.findById(textQuestionID);
        survey.removeQuestion(textQuestion);
        surveyService.save(survey);

        return survey;
    }

    @GetMapping(value = "/TextQuestionController/test")
    @ResponseBody
    public String testTextQuestionController() {
        return "TextQuestionController is working";
    }

}
