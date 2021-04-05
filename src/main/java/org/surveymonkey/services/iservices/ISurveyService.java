package org.surveymonkey.services.iservices;

import org.surveymonkey.models.Survey;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISurveyService {

    Survey findById(long id);

    Survey save(Survey survey);

    Iterable<Survey> findAll();

    HashMap<Integer, ArrayList<Integer>> getSurveyStatistics(int surveyId);

}