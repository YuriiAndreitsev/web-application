package com.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    public NewTopic testKafkaTopic() {
        return TopicBuilder.name("testTopic").build();
    }
}
