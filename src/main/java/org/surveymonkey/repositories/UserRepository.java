package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.surveymonkey.models.applicationUser;

public interface UserRepository extends PagingAndSortingRepository<applicationUser, Long> {

    applicationUser findById(long id);

    applicationUser findByName(String name);

}