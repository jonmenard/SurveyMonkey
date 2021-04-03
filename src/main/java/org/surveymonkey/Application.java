package org.surveymonkey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.surveymonkey.kafka.Producer;
import org.surveymonkey.kafka.SampleMessage;

/**
 * Drives the application.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public ApplicationRunner runner(Producer producer) {
        return (args) -> {
            for(int i = 1; i < 20; i++) {
                producer.send(new SampleMessage(i, "A simple test message"));
            }
        };
    }

}