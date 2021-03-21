package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.NumberQuestion;
import org.surveymonkey.repositories.NumberQuestionRepository;
import org.surveymonkey.services.iservices.INumberQuestionService;

@Service
public class NumberQuestionService implements INumberQuestionService {

    @Autowired
    NumberQuestionRepository numberQuestionRepository;

    @Override
    public NumberQuestion findById(long id) {
        return numberQuestionRepository.findById(id);
    }

}