package org.surveymonkey.services.iservices;

import org.surveymonkey.models.TransactionMessage;

public interface ITransactionMessageService {
    TransactionMessage save(TransactionMessage transactionMessage);
}
