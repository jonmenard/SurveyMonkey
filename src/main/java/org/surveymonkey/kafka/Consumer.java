package org.surveymonkey.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.surveymonkey.models.TransactionMessage;
import org.surveymonkey.services.iservices.ITransactionMessageService;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Component
public class Consumer {

    @Autowired
    private ITransactionMessageService transactionMessageService;


    @KafkaListener(topics = {"EndUser","Error","Question", "Survey"})
    public void processSyscProjectMessage(String message, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String time, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        Timestamp timestamp = new Timestamp(Long.parseLong(time));
        TransactionMessage tm = new TransactionMessage(topics.get(0), message, timestamp);
        transactionMessageService.save(tm);
    }

}
