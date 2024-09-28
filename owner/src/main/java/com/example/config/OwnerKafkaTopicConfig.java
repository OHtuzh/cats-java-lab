package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class OwnerKafkaTopicConfig {

    // Топик для создания владельца (запрос)
    @Bean
    public NewTopic ownerCreateTopic() {
        return TopicBuilder.name("owner-create")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для создания владельца (ответ)
    @Bean
    public NewTopic ownerCreateResponseTopic() {
        return TopicBuilder.name("owner-create-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для чтения информации о владельце (запрос)
    @Bean
    public NewTopic ownerReadTopic() {
        return TopicBuilder.name("owner-read")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для чтения информации о владельце (ответ)
    @Bean
    public NewTopic ownerReadResponseTopic() {
        return TopicBuilder.name("owner-read-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для удаления владельца (запрос)
    @Bean
    public NewTopic ownerDeleteTopic() {
        return TopicBuilder.name("owner-delete")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для удаления владельца (ответ)
    @Bean
    public NewTopic ownerDeleteResponseTopic() {
        return TopicBuilder.name("owner-delete-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для получения текущего владельца (запрос)
    @Bean
    public NewTopic ownerGetCurrentTopic() {
        return TopicBuilder.name("owner-get-current")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для получения текущего владельца (ответ)
    @Bean
    public NewTopic ownerGetCurrentResponseTopic() {
        return TopicBuilder.name("owner-get-current-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Добавьте другие топики по аналогии, если они нужны
}
