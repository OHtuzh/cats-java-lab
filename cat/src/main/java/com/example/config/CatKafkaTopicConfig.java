package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CatKafkaTopicConfig {

    // Топик для создания кота (запрос)
    @Bean
    public NewTopic catCreateTopic() {
        return TopicBuilder.name("cat-create")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для создания кота (ответ)
    @Bean
    public NewTopic catCreateResponseTopic() {
        return TopicBuilder.name("cat-create-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для чтения информации о коте (запрос)
    @Bean
    public NewTopic catReadTopic() {
        return TopicBuilder.name("cat-read")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для чтения информации о коте (ответ)
    @Bean
    public NewTopic catReadResponseTopic() {
        return TopicBuilder.name("cat-read-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для удаления кота (запрос)
    @Bean
    public NewTopic catDeleteTopic() {
        return TopicBuilder.name("cat-delete")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для удаления кота (ответ)
    @Bean
    public NewTopic catDeleteResponseTopic() {
        return TopicBuilder.name("cat-delete-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для фильтрации котов (запрос)
    @Bean
    public NewTopic catFilterTopic() {
        return TopicBuilder.name("cat-filter")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для фильтрации котов (ответ)
    @Bean
    public NewTopic catFilterResponseTopic() {
        return TopicBuilder.name("cat-filter-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для добавления друга кота (запрос)
    @Bean
    public NewTopic catAddFriendTopic() {
        return TopicBuilder.name("cat-add-friend")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Топик для добавления друга кота (ответ)
    @Bean
    public NewTopic catAddFriendResponseTopic() {
        return TopicBuilder.name("cat-add-friend-response")
                .partitions(1)
                .replicas(1)
                .build();
    }

    // Добавьте другие топики по аналогии, если они нужны
}
