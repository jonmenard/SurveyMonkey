package org.surveymonkey.services.iservices;

import org.surveymonkey.kafka.KafkaAnalytics;

public interface IKafkaAnalyticsService {
     KafkaAnalytics save(KafkaAnalytics KafkaAnalytics);

     KafkaAnalytics findTopByOrderByIdDesc();

     KafkaAnalytics findById(long id);

}
