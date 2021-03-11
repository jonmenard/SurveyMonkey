package repositories;

import models.Question;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface QuestionRepository<T extends Question> extends PagingAndSortingRepository<T, Long> {

    T findById(long id);

    T findByQuestion(String question);

}
