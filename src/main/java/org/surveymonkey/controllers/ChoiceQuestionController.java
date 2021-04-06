package org.surveymonkey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.surveymonkey.kafka.Producer;
import org.surveymonkey.kafka.Message;
import org.surveymonkey.models.ChoiceQuestion;
import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;
import org.surveymonkey.services.iservices.IQuestionService;
import org.surveymonkey.services.iservices.ISurveyService;

import java.util.List;

@Controller
public class ChoiceQuestionController {

    @Autowired
    private IQuestionService questionService;

    @Autowired
    private ISurveyService surveyService;

    @Autowired
    private Producer producer;


    private final String TOPIC = "Question";

    @GetMapping(value = "/survey/{surveyID}/choicequestion/{questionID}/choices")
    public String getChangeBoundsTemplate(@PathVariable long surveyID, @PathVariable int questionID, Model model) {
        Survey survey = surveyService.findById(surveyID);
        model.addAttribute("survey", survey);
        ChoiceQuestion question = (ChoiceQuestion) survey.findQuestion(questionID);
        model.addAttribute("question", question);
        return "addQuestionChoice";
    }

    @PostMapping(value = "/survey/{surveyID}/choicequestion/{questionID}/choices")
    public String postChangeBoundsTemplate(@PathVariable long surveyID, @PathVariable int questionID, @RequestParam String choice) {
        Survey survey = surveyService.findById(surveyID);
        ChoiceQuestion question = (ChoiceQuestion) survey.findQuestion(questionID);
        question.addChoice(choice);
        surveyService.save(survey);

        String message = "Adding choice: '" + choice + "' to question: " + questionID + "in survey: " + surveyID;
        sendMessage(message);

        return "redirect:/survey/" + surveyID + "/choicequestion/" + questionID + "/choices";
    }

    @PostMapping(value = "/survey/{surveyID}/choicequestion", produces = MediaType.APPLICATION_JSON_VALUE)
    public String postNumberQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyService.findById(surveyID);
        ChoiceQuestion choiceQuestion = new ChoiceQuestion(question);
        survey.addQuestion(choiceQuestion);
        surveyService.save(survey);

        List<Question> questions = survey.getQuestions();
        choiceQuestion = (ChoiceQuestion) questions.get(questions.size() - 1);

        String message = "Adding  question: '" + question + "' to survey: " + surveyID;
        sendMessage(message);

        return "redirect:/survey/" + surveyID + "/choicequestion/" + choiceQuestion.getId() + "/choices";
    }

    @DeleteMapping(value = "/survey/{surveyID}/choicequestion/{choiceQuestionID}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Survey deleteChoiceQuestion(@PathVariable long surveyID, @PathVariable long choiceQuestionID) {
        Survey survey = surveyService.findById(surveyID);
        Question choiceQuestion = questionService.findById(choiceQuestionID);
        survey.removeQuestion(choiceQuestion);
        surveyService.save(survey);

        String message = "Deleting question: " + choiceQuestionID + "from survey: " + surveyID;
        sendMessage(message);
        return survey;
    }

    @GetMapping(value = "/ChoiceQuestionController/test")
    @ResponseBody
    public String testChoiceQuestionController() {
        return "ChoiceQuestionController is working";
    }

    public void sendMessage(String message){
        producer.send(TOPIC, new Message(0, message));
    }
}