package com.example.services;

import com.example.dto.CreateOwnerRequest;
import com.example.dto.OwnerDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
@RequiredArgsConstructor
public class OwnerKafkaConsumerService {

    private final OwnerService ownerService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "owner-create", groupId = "owner_group")
    public void processCreateOwner(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CreateOwnerRequest request = objectMapper.readValue(message, CreateOwnerRequest.class);
        OwnerDto ownerDto = ownerService.createOwner(request.getName(), request.getBirthday());

        // Отправляем объект OwnerDto с пользовательским заголовком типа
        kafkaTemplate.send(MessageBuilder.withPayload(objectMapper.writeValueAsString(ownerDto))
                .setHeader(KafkaHeaders.TOPIC, "owner-create-response")
                .setHeader("type", OwnerDto.class.getName()) // Пользовательский заголовок "type"
                .build());
    }

    @KafkaListener(topics = "owner-read", groupId = "owner_group")
    public void processReadOwner(Integer ownerId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OwnerDto ownerDto = ownerService.getOwner(ownerId);

        // Отправляем объект OwnerDto с пользовательским заголовком типа
        kafkaTemplate.send(MessageBuilder.withPayload(objectMapper.writeValueAsString(ownerDto))
                .setHeader(KafkaHeaders.TOPIC, "owner-read-response")
                .setHeader("type", OwnerDto.class.getName()) // Пользовательский заголовок "type"
                .build());
    }

    @KafkaListener(topics = "owner-delete", groupId = "owner_group")
    public void processDeleteOwner(Integer ownerId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        ownerService.deleteOwner(ownerId);

        // Отправляем строку с информацией об удалении с указанием типа String
        kafkaTemplate.send(MessageBuilder.withPayload("Owner deleted successfully")
                .setHeader(KafkaHeaders.TOPIC, "owner-delete-response")
                .setHeader("type", String.class.getName()) // Пользовательский заголовок "type"
                .build());
    }

    @KafkaListener(topics = "owner-get-current", groupId = "owner_group")
    public void processGetCurrentOwner(Integer ownerId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        OwnerDto ownerDto = ownerService.getCurrentOwner(ownerId);

        // Отправляем объект OwnerDto с пользовательским заголовком типа
        kafkaTemplate.send(MessageBuilder.withPayload(ownerDto.toString())
                .setHeader(KafkaHeaders.TOPIC, "owner-get-current-response")
                .setHeader("type", String.class.getName()) // Пользовательский заголовок "type"
                .build());
    }
}
