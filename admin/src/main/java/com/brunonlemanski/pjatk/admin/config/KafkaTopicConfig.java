package com.brunonlemanski.pjatk.admin.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.address}")
    private String bootstrapAddress;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(config);
    }

    @Bean
    public NewTopic bolidTopic() {
        return new NewTopic("bolid", 1, (short) 1);
    }

    @Bean
    public NewTopic raceTopic() {
        return new NewTopic("race", 1, (short) 1);
    }

    @Bean
    public NewTopic engineTopic() { return new NewTopic("engine", 1, (short) 1); }

}