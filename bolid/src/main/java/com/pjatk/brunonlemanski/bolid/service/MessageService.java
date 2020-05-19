package com.pjatk.brunonlemanski.bolid.service;

import com.pjatk.brunonlemanski.bolid.BolidApplication;
import com.pjatk.brunonlemanski.bolid.model.Bolid;
import com.pjatk.brunonlemanski.bolid.model.Race;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class MessageService {

    @Value(value = "${kafka.topic_bolid}")
    private String TOPIC_BOLID;

    @Value(value = "${kafka.topic_race}")
    private String TOPIC_RACE;

    @Value(value = "${kafka.topic_test}")
    private String TOPIC_TEST;

    private static Long start = System.currentTimeMillis();

    private static Logger LOG = LoggerFactory
            .getLogger(BolidApplication.class);

    @Autowired
    private KafkaTemplate<String, Bolid> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Race> kafkaTemplateRace;


    @Scheduled(fixedDelay = 15000)
    public void sendInfoOnTopic() {

        Bolid bolid = generateValue();
        Long actualTime = getActualTime();

        Race race = new Race( actualTime, getLap(Math.toIntExact(actualTime)));

        //kafkaTemplate.send(TOPIC_TEST, race);

        ListenableFuture<SendResult<String,Bolid>> futureBolid = kafkaTemplate.send(TOPIC_BOLID, bolid);

        ListenableFuture<SendResult<String, Race>> futureRace = kafkaTemplateRace.send(TOPIC_RACE, race);


        futureBolid.addCallback(new ListenableFutureCallback<SendResult<String, Bolid>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Message in topic: '" + TOPIC_RACE.toUpperCase() + "' has not been sent. Check logs: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Bolid> stringBolidSendResult) {
                LOG.info("Message in topic: '" + TOPIC_RACE.toUpperCase() + "' has been sent.");
            }
        });

        futureRace.addCallback(new ListenableFutureCallback<SendResult<String, Race>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("Message in topic: '" + TOPIC_BOLID.toUpperCase() + "' hasn not been sent. Check logs: " + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Race> stringRaceSendResult) {
                LOG.info("Message in topic: '" + TOPIC_BOLID.toUpperCase() + "' has been sent.");
            }
        });
    }

    private Bolid generateValue() {

        double tempEngine = bolidParametersGenerator(90.0, 103.0); // degree C
        double tirePressure = bolidParametersGenerator(19, 23);    // PSI
        double oilPressure = bolidParametersGenerator(190, 410);   // kPa


        return new Bolid(tempEngine, tirePressure, oilPressure);
    }

    private double bolidParametersGenerator(double min, double max) {
        return (Math.random() * ((max-min)+1) + min);
    }

    private Long getActualTime() {
        return (System.currentTimeMillis() - start) / 1000;
    }

    private Integer getLap(Integer x) {
        Integer i = 0;

        while(x >= 0) {
            x = x - 60;
            i++;
        }

        return i;
    }
}