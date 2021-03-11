package org.surveymonkey.repositories;

import org.surveymonkey.models.NumberQuestion;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface NumberQuestionRepository extends QuestionRepository<NumberQuestion> {
}
