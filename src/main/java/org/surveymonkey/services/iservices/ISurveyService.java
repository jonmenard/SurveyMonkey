package org.surveymonkey.services.iservices;

import org.surveymonkey.models.Survey;

public interface ISurveyService {

    Survey findById(long id);

    Survey save(Survey survey);

    Iterable<Survey> findAll();

}