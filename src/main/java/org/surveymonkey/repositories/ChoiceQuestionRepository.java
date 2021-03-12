package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.surveymonkey.models.ChoiceQuestion;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ChoiceQuestionRepository extends QuestionRepository<ChoiceQuestion>, PagingAndSortingRepository<ChoiceQuestion, Long> {
}
