package org.surveymonkey.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.surveymonkey.models.TransactionMessage;

public interface TransactionMessageRepository extends PagingAndSortingRepository<TransactionMessage, Long> {


}


