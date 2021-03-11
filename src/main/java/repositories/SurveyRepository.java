package repositories;

import models.Survey;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SurveyRepository extends PagingAndSortingRepository<Survey, Long> {

    Survey findById(long id);

}
