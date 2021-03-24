package org.surveymonkey.services.iservices;

import org.surveymonkey.models.Question;

public interface IQuestionService {

    Question findById(long id);

    Question save(Question question);

}