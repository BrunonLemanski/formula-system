package com.brunonlemanski.pjatk.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static com.brunonlemanski.pjatk.admin.config.Alerts.FAILED_MESSAGE;
import static com.brunonlemanski.pjatk.admin.config.Alerts.SUCCESS_MESSAGE;

@Service
public class ProducerService {

    private final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Value(value = "${kafka.engine_alert_topic}")
    private String TOPIC_ENGINE;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendOnTopic(String alert) {

        ListenableFuture<SendResult<String, String>> futureAlert = kafkaTemplate.send(TOPIC_ENGINE, alert);

        futureAlert.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error(FAILED_MESSAGE + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringSendResult) {
                logger.info(SUCCESS_MESSAGE);
            }
        });
    }
}
