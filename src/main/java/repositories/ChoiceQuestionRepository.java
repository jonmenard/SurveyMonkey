package repositories;

import models.ChoiceQuestion;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ChoiceQuestionRepository extends QuestionRepository<ChoiceQuestion> {
}
