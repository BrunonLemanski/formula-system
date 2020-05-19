package com.pjatk.brunonlemanski.bolid.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @Value(value = "${kafka.request_topic}")
    private String requestTopic;

    @Value(value = "${kafka.reply_topic}")
    private String replyTopic;

    @Autowired
    private ReplyingKafkaTemplate<String, String, String> requestReplyKafkaTemplate;

    @GetMapping("/pitstop")
    public void pitStopRequest() {

        //create producer record
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(requestTopic, "Prośba o zjazd -> PIT-STOP");

        //set reply topic in header
        record.headers()
                .add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, replyTopic.getBytes()));

        //post request to kafka topic, and asynchronously
        RequestReplyFuture<String, String, String> sendAndReceive = requestReplyKafkaTemplate.sendAndReceive(record);
        sendAndReceive.addCallback(new ListenableFutureCallback<ConsumerRecord<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {

            }

            @Override
            public void onSuccess(ConsumerRecord<String, String> stringStringConsumerRecord) {
                System.out.println("Odpowiedź: " + stringStringConsumerRecord.value());
            }
        });
    }
}
