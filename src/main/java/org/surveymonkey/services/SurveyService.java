package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.Survey;
import org.surveymonkey.repositories.SurveyRepository;
import org.surveymonkey.services.iservices.ISurveyService;

@Service
public class SurveyService implements ISurveyService {

    @Autowired
    SurveyRepository surveyRepository;

    @Override
    public Survey findById(long id) {
        return surveyRepository.findById(id);
    }

    @Override
    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Iterable<Survey> findAll(){return surveyRepository.findAll();}

}