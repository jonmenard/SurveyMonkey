package org.surveymonkey.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.surveymonkey.models.Question;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {

    Question findById(long id);

    Question findByQuestion(String question);

}
