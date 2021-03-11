package controllers;

import models.ChoiceQuestion;
import models.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repositories.ChoiceQuestionRepository;
import repositories.SurveyRepository;

@Controller
public class ChoiceQuestionController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ChoiceQuestionRepository choiceQuestionRepository;

    @PostMapping(value = "/survey/{surveyID}/choicequestion", produces = "application/json")
    @ResponseBody
    public Survey postChoiceQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyRepository.findById(surveyID);
        survey.addQuestion(new ChoiceQuestion(question));
        surveyRepository.save(survey);
        return survey;
    }

    @DeleteMapping(value = "/survey/{surveyID}/choicequestion/{choiceQuestionID}", produces = "application/json")
    @ResponseBody
    public Survey deleteChoiceQuestion(@PathVariable long surveyID, @PathVariable long choiceQuestionID) {
        Survey survey = surveyRepository.findById(surveyID);
        ChoiceQuestion choiceQuestion = choiceQuestionRepository.findById(choiceQuestionID);
        survey.removeQuestion(choiceQuestion);
        surveyRepository.save(survey);
        return survey;
    }

}
