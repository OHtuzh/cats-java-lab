package com.example.services;

import com.example.dto.CatDto;
import com.example.requests.CreateCatRequest;
import com.example.requests.FilterRequest;
import com.example.requests.FriendRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
@RequiredArgsConstructor
public class CatKafkaConsumerService {

    private final CatService catService;
    private final CatCheckUserService catCheckUserService;
    private final KafkaProducerService kafkaProducerService;

    @KafkaListener(topics = "cat-create", groupId = "cat_group")
    public void processCreateCat(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CreateCatRequest request = objectMapper.readValue(message, CreateCatRequest.class);
        CatDto catDto = catCheckUserService.createCatCheckUser(
                request.getName(), request.getOwnerId(), request.getBirthday(), request.getBreed(), request.getColor(), request.getId()
        );
        kafkaProducerService.sendMessage("cat-create-response", objectMapper.writeValueAsString(catDto));
    }

    @KafkaListener(topics = "cat-read", groupId = "cat_group")
    public void processReadCat(Integer catId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        CatDto catDto = catService.getCat(catId);
        kafkaProducerService.sendMessage("cat-read-response", objectMapper.writeValueAsString(catDto));
    }

    @KafkaListener(topics = "cat-delete", groupId = "cat_group")
    public void processDeleteCat(Integer catId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        catService.deleteCat(catId);
        kafkaProducerService.sendMessage("cat-delete-response", "Cat deleted successfully");
    }

    @KafkaListener(topics = "cat-filter", groupId = "cat_group")
    public void processFilteredCats(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        FilterRequest filterRequest = objectMapper.readValue(message, FilterRequest.class);
        var filteredCats = catService.getByParams(filterRequest.getName(), filterRequest.getUuid(), filterRequest.getBirthDay(), filterRequest.getBreed());
        kafkaProducerService.sendMessage("cat-filter-response", objectMapper.writeValueAsString(filteredCats));
    }

    @KafkaListener(topics = "cat-add-friend", groupId = "cat_group")
    public void processAddFriend(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        FriendRequest friendRequest = objectMapper.readValue(message, FriendRequest.class);
        catService.addFriend(friendRequest.getCatUuid(), friendRequest.getCatFriendUuid());
        kafkaProducerService.sendMessage("cat-add-friend-response", "Friend added successfully");
    }
}
