import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

// Testing DB query functionality using REST approach
// Will implement view controllers after confirming functionality
@NoRepositoryBean
public interface QuestionRepository<T extends Question> extends CrudRepository<T, String>{
    T findByQuestion(String question);
}
