package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.TransactionMessage;
import org.surveymonkey.repositories.TransactionMessageRepository;
import org.surveymonkey.services.iservices.ITransactionMessageService;

@Service
public class TransactionMessageService implements ITransactionMessageService {


    @Autowired
    TransactionMessageRepository transactionMessageRepository;

    @Override
    public TransactionMessage save(TransactionMessage transactionMessage) {
        return transactionMessageRepository.save(transactionMessage);
    }
}
