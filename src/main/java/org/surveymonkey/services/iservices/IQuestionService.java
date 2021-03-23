package org.surveymonkey.services.iservices;

import org.surveymonkey.models.Question;
import org.surveymonkey.models.Survey;

public interface IQuestionService {

    Question findById(long id);
    Question save(Question question);
}