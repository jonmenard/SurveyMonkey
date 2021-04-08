package org.surveymonkey.services.iservices;

import org.surveymonkey.models.EndUser;
import org.surveymonkey.models.Survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ISurveyService {

    Survey findById(long id);

    Survey save(Survey survey);

    Iterable<Survey> findAll();

    HashMap<Integer, ArrayList<Integer>> getSurveyStatistics(int surveyId);

    List<Survey> findSurveysByUser(EndUser endUser);

    void swapQuestion(int surveyId, int questionId, String direction);
}