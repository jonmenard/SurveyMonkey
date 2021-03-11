package controllers;

import models.NumberQuestion;
import models.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repositories.NumberQuestionRepository;
import repositories.SurveyRepository;

@Controller
public class NumberQuestionController {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private NumberQuestionRepository numberQuestionRepository;

    @PostMapping(value = "/survey/{surveyID}/numberquestion", produces = "application/json")
    @ResponseBody
    public Survey postNumberQuestion(@PathVariable long surveyID, @RequestParam String question) {
        Survey survey = surveyRepository.findById(surveyID);
        survey.addQuestion(new NumberQuestion(question));
        surveyRepository.save(survey);
        return survey;
    }

    @DeleteMapping(value = "/survey/{surveyID}/numberquestion/{numberQuestionID}", produces = "application/json")
    @ResponseBody
    public Survey deleteNumberQuestion(@PathVariable long surveyID, @PathVariable long numberQuestionID) {
        Survey survey = surveyRepository.findById(surveyID);
        NumberQuestion numberQuestion = numberQuestionRepository.findById(numberQuestionID);
        survey.removeQuestion(numberQuestion);
        surveyRepository.save(survey);
        return survey;
    }

}
