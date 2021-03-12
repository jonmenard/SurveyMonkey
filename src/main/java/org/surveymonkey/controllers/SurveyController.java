package org.surveymonkey.controllers;

import org.springframework.ui.Model;
import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.repositories.SurveyRepository;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepository;

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/survey/{surveyID}", produces = "application/json")
    @ResponseBody
    public Survey getSurvey(@PathVariable long surveyID) {
        return surveyRepository.findById(surveyID);
    }

    @GetMapping(value = "/survey/{surveyID}")
    public String getSurvey(Model model, @PathVariable long surveyID){
        model.addAttribute("survey", surveyRepository.findById(surveyID));
        model.addAttribute("questions", surveyRepository.findById(surveyID).getQuestionList());
        return "surveyTable";
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

    @PostMapping(value="/survey")
    public String postSurvey(Model model){
        Survey survey = new Survey();
        surveyRepository.save(survey);
        model.addAttribute("survey", survey);
        return "surveyCreated";
    }


}