//package org.surveymonkey.kafka;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Component;
//import org.surveymonkey.models.TransactionMessage;
//import org.surveymonkey.services.iservices.IKafkaAnalyticsService;
//import org.surveymonkey.services.iservices.ITransactionMessageService;
//
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.util.List;
//
//@Component
//public class Consumer {
//
//    @Autowired
//    private ITransactionMessageService transactionMessageService;
//
//    @Autowired
//    private IKafkaAnalyticsService kafkaAnalyticsService;
//
//
//    @KafkaListener(topics = {"EndUser","Error","Question", "Survey"})
//    public void processSyscProjectMessage(String message, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String time, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
//        Timestamp timestamp = new Timestamp(Long.parseLong(time));
//        TransactionMessage tm = new TransactionMessage(topics.get(0), message, timestamp);
//        transactionMessageService.save(tm);
//    }
//
//
//    @KafkaListener(topics = {"updatesAndInserts"})
//    public void updataKafkaAnalyticsUpdatesAndInserts(String message, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String time, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
//        KafkaAnalytics kafkaAnalytics =  kafkaAnalyticsService.findById(1349);
//
//        if(message.equals("\"update\"")){
//            kafkaAnalytics.setDatabaseUpdate(kafkaAnalytics.getDatabaseUpdate() + 1);
//        }else if(message.equals("\"select\"")){
//            kafkaAnalytics.setDatabaseSelect(kafkaAnalytics.getDatabaseSelect() + 1);
//        }else{
//            return;
//        }
//
//        kafkaAnalyticsService.save(kafkaAnalytics);
//
//    }
//
//    @KafkaListener(topics = {"SurveyInformation"})
//    public void updataKafkaAnalyticsSurveyInformation(String message, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String time, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
//        KafkaAnalytics kafkaAnalytics =  kafkaAnalyticsService.findById(1349);
//
//        if(message.equals("\"answered\"")){
//            kafkaAnalytics.setSurveyAnswered(kafkaAnalytics.getSurveyAnswered() + 1);
//        }else if(message.equals("\"created\"")){
//            kafkaAnalytics.setSurveyCreated(kafkaAnalytics.getSurveyCreated() + 1);
//        }else{
//            return;
//        }
//
//        kafkaAnalyticsService.save(kafkaAnalytics);
//    }
//
//    @KafkaListener(topics = {"PageVisited"})
//    public void updataKafkaAnalyticsPagesVisited(String message, @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String time, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
//        KafkaAnalytics kafkaAnalytics =  kafkaAnalyticsService.findById(1349);
//
//        if(message.equals("\"addQuestionChoice\"")){
//            kafkaAnalytics.setAddQuestionChoiceTemplateVisited(kafkaAnalytics.getAddQuestionChoiceTemplateVisited() + 1);
//        }else if(message.equals("\"changeQuestionBounds\"")){
//            kafkaAnalytics.setChangeQuestionBoundsTemplateVisited(kafkaAnalytics.getChangeQuestionBoundsTemplateVisited() + 1);
//        }else if(message.equals("\"createQuestion\"")){
//            kafkaAnalytics.setCreatQuestionTemplateVisited(kafkaAnalytics.getCreatQuestionTemplateVisited() + 1);
//        }else if(message.equals("\"createSurvey\"")){
//            kafkaAnalytics.setCreateSurveyTemplateVisited(kafkaAnalytics.getCreateSurveyTemplateVisited() + 1);
//        }else if(message.equals("\"createUser\"")){
//            kafkaAnalytics.setCreateUserTemplateVisited(kafkaAnalytics.getCreateUserTemplateVisited() + 1);
//        }else if(message.equals("\"displayAllOpenSurveys\"")){
//            kafkaAnalytics.setDisplayAllOpenSurveysTemplateVisited(kafkaAnalytics.getDisplayAllOpenSurveysTemplateVisited() + 1);
//        }else if(message.equals("\"displayUserSurveys\"")){
//            kafkaAnalytics.setDisplayUserSurveysTemplateVisited(kafkaAnalytics.getDisplayUserSurveysTemplateVisited() + 1);
//        }else if(message.equals("\"doSurvey\"")){
//            kafkaAnalytics.setDoSurveyTemplateVisited(kafkaAnalytics.getDoSurveyTemplateVisited() + 1);
//        }else if(message.equals("\"error\"")){
//            kafkaAnalytics.setErrorTemplateVisited(kafkaAnalytics.getErrorTemplateVisited() + 1);
//        }else if(message.equals("\"fillSurvey\"")){
//            kafkaAnalytics.setFillSurveyTemplateVisited(kafkaAnalytics.getFillSurveyTemplateVisited() + 1);
//        }else if(message.equals("\"index\"")){
//            kafkaAnalytics.setIndexTemplateVisited(kafkaAnalytics.getIndexTemplateVisited() + 1);
//        }else if(message.equals("\"responseThankYou\"")){
//            kafkaAnalytics.setResponseThankYouTemplateVisited(kafkaAnalytics.getResponseThankYouTemplateVisited() + 1);
//        }else if(message.equals("\"surveyCreated\"")){
//            kafkaAnalytics.setFillSurveyTemplateVisited(kafkaAnalytics.getFillSurveyTemplateVisited() + 1);
//        }else if(message.equals("\"viewResponses\"")){
//            kafkaAnalytics.setViewResponsesTemplateVisited(kafkaAnalytics.getViewResponsesTemplateVisited() + 1);
//        }else{
//            return;
//        }
//
//        kafkaAnalyticsService.save(kafkaAnalytics);
//    }
//
//
//
//}
