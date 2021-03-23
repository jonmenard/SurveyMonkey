package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.surveymonkey.models.*;
import org.surveymonkey.services.iservices.ISurveyService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

        List<Question> questionList = surveyService.findById(surveyID).getQuestionList();
        ArrayList<TextQuestion> textQuestionList = new ArrayList<TextQuestion>();
        ArrayList<NumberQuestion> numberQuestionList = new ArrayList<NumberQuestion>();
        ArrayList<ChoiceQuestion> choiceQuestionList = new ArrayList<ChoiceQuestion>();

        for(int index = 0; index < questionList.size(); index++){
            Question question = questionList.get(index);
            Question.QuestionType type = question.getType();
            if(type.compareTo(Question.QuestionType.TEXT) == 0){
                textQuestionList.add((TextQuestion) question);
            }else if(type.compareTo(Question.QuestionType.NUMBER) == 0){
                numberQuestionList.add((NumberQuestion) question);
            }else if(type.compareTo(Question.QuestionType.CHOICE) == 0){
                choiceQuestionList.add((ChoiceQuestion) question);
            }
        }

        model.addAttribute("textQuestions", textQuestionList);
        model.addAttribute("numberQuestions", numberQuestionList);
        model.addAttribute("choiceQuestions", choiceQuestionList);

        return "doSurvey";
        //return "surveyTable";
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

    @PostMapping(value = "/survey/{surveyID}/createQuestion")
    public ModelAndView addQuestion(HttpServletRequest request, @PathVariable long surveyID, @RequestParam String questionType , @RequestParam String question){
        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

        return new ModelAndView("redirect:/survey/" + surveyID + "/" +  questionType);
    }



    @GetMapping(value = "/SurveyControllerController/test")
    @ResponseBody
    public String testSurveyController() {
        return "SurveyController is working";
    }

}