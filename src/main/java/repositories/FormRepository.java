package repositories;

import models.Form;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FormRepository extends PagingAndSortingRepository<Form, Long> {

    Form findById(long id);

}
