package com.example.owner.services;

import com.example.owner.dto.CreateOwnerRequest;
import com.example.owner.dto.OwnerDto;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
@RequiredArgsConstructor
public class OwnerKafkaConsumerService {

    private final OwnerService ownerService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "owner-create", groupId = "owner_group")
    public void processCreateOwner(CreateOwnerRequest request, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        OwnerDto ownerDto = ownerService.createOwner(request.getName(), request.getBirthday());
        kafkaTemplate.send("owner-create-response", ownerDto);
    }

    @KafkaListener(topics = "owner-read", groupId = "owner_group")
    public void processReadOwner(Integer ownerId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        OwnerDto ownerDto = ownerService.getOwner(ownerId);
        kafkaTemplate.send("owner-read-response", ownerDto);
    }

    @KafkaListener(topics = "owner-delete", groupId = "owner_group")
    public void processDeleteOwner(Integer ownerId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        ownerService.deleteOwner(ownerId);
        kafkaTemplate.send("owner-delete-response", "Owner deleted successfully");  // Отправляем ответ
    }

    @KafkaListener(topics = "owner-get-current", groupId = "owner_group")
    public void processGetCurrentOwner(Integer ownerId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        OwnerDto ownerDto = ownerService.getCurrentOwner(ownerId);
        kafkaTemplate.send("owner-get-current-response", ownerDto);
    }
}
