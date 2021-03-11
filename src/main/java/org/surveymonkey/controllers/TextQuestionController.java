package org.surveymonkey.controllers;

import org.surveymonkey.models.Survey;
import org.surveymonkey.models.TextQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.repositories.SurveyRepository;
import org.surveymonkey.repositories.TextQuestionRepository;

@Controller
public class TextQuestionController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private TextQuestionRepository textQuestionRepository;

    @PostMapping(value = "/survey/{surveyID}/textquestion", produces = "application/json")
    @ResponseBody
    public Survey postTextQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyRepository.findById(surveyID);
        survey.addQuestion(new TextQuestion(question));
        surveyRepository.save(survey);
        return survey;
    }

    @DeleteMapping(value = "/survey/{surveyID}/textquestion/{textQuestionID}", produces = "application/json")
    @ResponseBody
    public Survey deleteTextQuestion(@PathVariable long surveyID, @PathVariable long textQuestionID) {
        Survey survey = surveyRepository.findById(surveyID);
        TextQuestion textQuestion = textQuestionRepository.findById(textQuestionID);
        survey.removeQuestion(textQuestion);
        surveyRepository.save(survey);
        return survey;
    }

}
