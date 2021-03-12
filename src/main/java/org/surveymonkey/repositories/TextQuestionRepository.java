package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.surveymonkey.models.TextQuestion;

@Transactional
public interface TextQuestionRepository extends QuestionRepository<TextQuestion>, PagingAndSortingRepository<TextQuestion, Long> {
}
