package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.surveymonkey.models.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    User findById(long id);

    User findByName(String name);

}