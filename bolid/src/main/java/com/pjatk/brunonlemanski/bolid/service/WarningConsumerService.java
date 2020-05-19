package com.pjatk.brunonlemanski.bolid.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WarningConsumerService {

    @KafkaListener(topics = "${kafka.engine_alert_topic}", groupId = "${kafka.group_engine_id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumerWarning(String message) {
        System.out.println("Warning: " + message);
    }
}
