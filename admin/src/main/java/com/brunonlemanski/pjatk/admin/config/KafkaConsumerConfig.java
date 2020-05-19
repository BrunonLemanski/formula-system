package com.brunonlemanski.pjatk.admin.config;

import com.brunonlemanski.pjatk.admin.model.Bolid;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value(value = "${kafka.address}")
    private String bootstrapAddress;

    @Value(value = "${kafka.group_bolid_id}")
    private String groupIdBolid;

    @Bean
    public ConsumerFactory<String, Bolid> consumerFactoryBolid() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, groupIdBolid);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), new JsonDeserializer<>(Bolid.class, false)); //use FALSE if thrown 'is not trusted in the packages' it depends on spring boot version
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Bolid>
            kafkaListenerContainerFactoryBolid() {
        ConcurrentKafkaListenerContainerFactory<String, Bolid> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryBolid());
        return factory;
    }
}
