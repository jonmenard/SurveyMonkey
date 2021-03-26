package org.surveymonkey.repositories;

import org.surveymonkey.models.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {

    Survey findById(long id);
}
