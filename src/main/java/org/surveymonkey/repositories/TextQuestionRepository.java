package org.surveymonkey.repositories;

import org.surveymonkey.models.TextQuestion;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TextQuestionRepository extends QuestionRepository<TextQuestion> {
}
