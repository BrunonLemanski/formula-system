package com.brunonlemanski.pjatk.admin.service;

import com.brunonlemanski.pjatk.admin.model.Bolid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.brunonlemanski.pjatk.admin.config.Alerts.*;


@Service
public class ConsumerService {

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Value(value = "${kafka.engine_alert_topic}")
    private String TOPIC_ENGINE;

    @Autowired
    private ProducerService producerService;


    @KafkaListener(topics = "${kafka.bolid_topic}", groupId = "${kafka.group_bolid_id}", containerFactory = "kafkaListenerContainerFactoryBolid")
    public void consumerBolid(Bolid bolid) {

        System.out.println("Engine parametres: " + bolid);

        if(bolid.getTempEngine() >= 101) {
            logger.warn(TEAM_WARNING + ENGINE_TEMP);

            producerService.sendOnTopic(ENGINE_TEMP);
        }

        if(bolid.getOilPressure() < 200 || bolid.getOilPressure() > 400) {
            logger.warn(TEAM_WARNING + OIL_PRESSURE);
            producerService.sendOnTopic(OIL_PRESSURE);
        }

        if(bolid.getTirePressure() > 23 || bolid.getTirePressure() < 19.5) {
            logger.warn(TEAM_WARNING + TIRE_PRESSURE);
            producerService.sendOnTopic(TIRE_PRESSURE);
        }
        System.out.println();
    }
}
