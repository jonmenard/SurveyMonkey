package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.models.ChoiceQuestion;
import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;
import org.surveymonkey.services.iservices.IChoiceQuestionService;
import org.surveymonkey.services.iservices.ISurveyService;

@Controller
public class ChoiceQuestionController {

    @Autowired
    private IChoiceQuestionService choiceQuestionService;

    @Autowired
    private ISurveyService surveyService;

    @PostMapping(value = "/survey/{surveyID}/choicequestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public String postChoiceQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyService.findById(surveyID);
        survey.addQuestion(new ChoiceQuestion(question));
        surveyService.save(survey);
        return "redirect:/survey/" + surveyID;
    }

    @DeleteMapping(value = "/survey/{surveyID}/choicequestion/{choiceQuestionID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey deleteChoiceQuestion(@PathVariable long surveyID, @PathVariable long choiceQuestionID) {
        Survey survey = surveyService.findById(surveyID);
        Question choiceQuestion = choiceQuestionService.findById(choiceQuestionID);
        survey.removeQuestion(choiceQuestion);
        surveyService.save(survey);
        return survey;
    }

    @GetMapping(value = "/ChoiceQuestionController/test")
    @ResponseBody
    public String testChoiceQuestionController() {
        return "ChoiceQuestionController is working";
    }

}
