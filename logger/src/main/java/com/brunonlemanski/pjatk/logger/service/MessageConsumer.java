package com.brunonlemanski.pjatk.logger.service;

import com.brunonlemanski.pjatk.logger.logger.DataLoggingToFile;
import com.brunonlemanski.pjatk.logger.model.Bolid;
import com.brunonlemanski.pjatk.logger.model.Race;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    DataLoggingToFile logger = new DataLoggingToFile();
/*
    @KafkaListener(topics = "${kafka.test_topic}", group = "${kafka.group_test_id}")
    public void consumerTest(String message) {
        System.out.println("From consumer message: " + message);
    }*/

    @KafkaListener(topics = "${kafka.bolid_topic}", group = "${kafka.group_bolid_id}", containerFactory = "kafkaListenerContainerFactoryBolid")
    public void consumerBolid(Bolid bolid) {
        System.out.println("Message from bolid: " + bolid);
        logger.log(bolid);
    }

    @KafkaListener(topics = "${kafka.race_topic}", group = "${kafka.group_race_id}", containerFactory = "kafkaListenerContainerFactoryRace")
    public void consumerRace(Race race) {
        System.out.println("Message from race: " + race);
        logger.log(race);
    }
}
