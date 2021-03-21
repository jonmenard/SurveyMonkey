package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.models.Survey;
import org.surveymonkey.services.iservices.ISurveyService;

@Controller
public class SurveyController {

    @Autowired
    private ISurveyService surveyService;

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
        model.addAttribute("questions", surveyService.findById(surveyID).getQuestionList());
        return "surveyTable";
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
        return survey;
    }

    @PostMapping(value = "/survey")
    public String postSurvey(Model model) {
        Survey survey = new Survey();
        surveyService.save(survey);
        model.addAttribute("survey", survey);
        return "surveyCreated";
    }

}