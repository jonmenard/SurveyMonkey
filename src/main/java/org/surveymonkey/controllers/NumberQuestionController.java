package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.models.NumberQuestion;
import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;
import org.surveymonkey.services.iservices.INumberQuestionService;
import org.surveymonkey.services.iservices.ISurveyService;

@Controller
public class NumberQuestionController {

    @Autowired
    private INumberQuestionService numberQuestionService;

    @Autowired
    private ISurveyService surveyService;

    @PostMapping(value = "/survey/{surveyID}/numberquestion", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey postNumberQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyService.findById(surveyID);
        survey.addQuestion(new NumberQuestion(question));
        surveyService.save(survey);
        return survey;
    }

    @DeleteMapping(value = "/survey/{surveyID}/numberquestion/{numberQuestionID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey deleteNumberQuestion(@PathVariable long surveyID, @PathVariable long numberQuestionID) {
        Survey survey = surveyService.findById(surveyID);
        Question numberQuestion = numberQuestionService.findById(numberQuestionID);
        survey.removeQuestion(numberQuestion);
        surveyService.save(survey);
        return survey;
    }

}
