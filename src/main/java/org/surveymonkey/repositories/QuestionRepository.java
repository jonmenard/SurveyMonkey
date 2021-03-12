package org.surveymonkey.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface QuestionRepository<T> extends Repository<T, Long> {

    T findById(long id);

    T findByQuestion(String question);

}
