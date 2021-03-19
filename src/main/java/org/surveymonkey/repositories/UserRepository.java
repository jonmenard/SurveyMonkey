package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import org.surveymonkey.models.TextQuestion;
import org.surveymonkey.models.User;

@Transactional
public interface UserRepository {
    User findById(long id);
    User findByName(String name);
}
