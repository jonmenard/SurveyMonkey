package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.ChoiceQuestion;
import org.surveymonkey.repositories.ChoiceQuestionRepository;
import org.surveymonkey.services.iservices.IChoiceQuestionService;

@Service
public class ChoiceQuestionService implements IChoiceQuestionService {

    @Autowired
    ChoiceQuestionRepository choiceQuestionRepository;

    @Override
    public ChoiceQuestion findById(long id) {
        return choiceQuestionRepository.findById(id);
    }

}