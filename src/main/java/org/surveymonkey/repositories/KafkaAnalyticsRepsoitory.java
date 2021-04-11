package org.surveymonkey.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.surveymonkey.kafka.KafkaAnalytics;

public interface KafkaAnalyticsRepsoitory extends PagingAndSortingRepository<KafkaAnalytics, Long> {

    KafkaAnalytics findTopByOrderByKafkaAnalysisId();

    KafkaAnalytics findByKafkaAnalysisId(long id);

}


