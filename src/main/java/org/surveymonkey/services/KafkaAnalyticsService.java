//package org.surveymonkey.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.surveymonkey.kafka.KafkaAnalytics;
//import org.surveymonkey.repositories.KafkaAnalyticsRepsoitory;
//import org.surveymonkey.services.iservices.IKafkaAnalyticsService;
//
//@Service
//public class KafkaAnalyticsService implements IKafkaAnalyticsService {
//
//    @Autowired
//    KafkaAnalyticsRepsoitory kafkaAnalyticsRepsoitory ;
//
//    @Override
//    public KafkaAnalytics save(KafkaAnalytics KafkaAnalytics) {
//        return kafkaAnalyticsRepsoitory.save(KafkaAnalytics);
//    }
//
//    @Override
//    public KafkaAnalytics findTopByOrderByIdDesc() {
//        return kafkaAnalyticsRepsoitory.findTopByOrderByKafkaAnalysisId();
//    }
//
//    @Override
//    public KafkaAnalytics findById(long id) {
//        return kafkaAnalyticsRepsoitory.findByKafkaAnalysisId(id);
//    }
//
//
//}
