package org.surveymonkey.controllers;

import org.surveymonkey.models.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.repositories.SurveyRepository;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @GetMapping(value = "/survey/{surveyID}", produces = "application/json")
    @ResponseBody
    public Survey getSurvey(@PathVariable long surveyID) {
        return surveyRepository.findById(surveyID);
    }

    @GetMapping("/survey")
    public String viewSurvey(@RequestParam long surveyID) {
        return "redirect:/survey/" + surveyID;
    }

    @PostMapping(value = "/survey", produces = "application/json")
    @ResponseBody
    public Survey postSurvey() {
        Survey survey = new Survey();
        surveyRepository.save(survey);
        return survey;
    }

}