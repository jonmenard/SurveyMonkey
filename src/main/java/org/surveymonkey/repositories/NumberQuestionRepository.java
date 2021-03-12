package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.surveymonkey.models.NumberQuestion;

@Transactional
public interface NumberQuestionRepository extends QuestionRepository<NumberQuestion>, PagingAndSortingRepository<NumberQuestion, Long> {
}
