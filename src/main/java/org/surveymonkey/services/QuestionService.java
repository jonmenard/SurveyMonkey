package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.Question;
import org.surveymonkey.repositories.QuestionRepository;
import org.surveymonkey.services.iservices.IQuestionService;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public Question findById(long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

}