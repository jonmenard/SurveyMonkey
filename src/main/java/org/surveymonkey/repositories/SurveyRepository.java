package org.surveymonkey.repositories;

import org.surveymonkey.models.EndUser;
import org.surveymonkey.models.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {

    Survey findById(long id);

    List<Survey> findByEndUser(EndUser end_User);
}
