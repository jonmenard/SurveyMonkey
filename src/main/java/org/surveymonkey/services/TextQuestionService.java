package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.TextQuestion;
import org.surveymonkey.repositories.TextQuestionRepository;
import org.surveymonkey.services.iservices.ITextQuestionService;

@Service
public class TextQuestionService implements ITextQuestionService {

    @Autowired
    TextQuestionRepository textQuestionRepository;

    @Override
    public TextQuestion findById(long id) {
        return textQuestionRepository.findById(id);
    }

}
