package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.surveymonkey.models.EndUser;

public interface EndUserRepository extends PagingAndSortingRepository<EndUser, Long> {

    EndUser findById(long id);

    EndUser findByName(String name);

}