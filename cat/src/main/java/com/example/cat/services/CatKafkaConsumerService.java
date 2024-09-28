package com.example.cat.services;

import com.example.cat.dto.CatDto;
import com.example.cat.dto.CreateCatRequest;
import com.example.cat.dto.FilterRequest;
import com.example.cat.dto.FriendRequest;
import com.example.cat.services.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.kafka.support.KafkaHeaders;

@Service
@RequiredArgsConstructor
public class CatKafkaConsumerService {

    private final CatService catService;
    private final CatCheckUserService catCheckUserService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "cat-create", groupId = "cat_group")
    public void processCreateCat(CreateCatRequest request, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        CatDto catDto = catCheckUserService.createCatCheckUser(request.getName(), request.getOwnerId(), request.getBirthday(), request.getBreed(), request.getColor(), request.getId());
        kafkaTemplate.send("cat-create-response", catDto);  // Отправляем ответ
    }

    @KafkaListener(topics = "cat-read", groupId = "cat_group")
    public void processReadCat(Integer catId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        CatDto catDto = catService.getCat(catId);
        kafkaTemplate.send("cat-read-response", catDto);  // Отправляем ответ
    }

    @KafkaListener(topics = "cat-delete", groupId = "cat_group")
    public void processDeleteCat(Integer catId, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        catService.deleteCat(catId);
        kafkaTemplate.send("cat-delete-response", "Cat deleted successfully");  // Отправляем ответ
    }

    @KafkaListener(topics = "cat-filter", groupId = "cat_group")
    public void processFilteredCats(FilterRequest filterRequest, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        var filteredCats = catService.getByParams(filterRequest.getName(), filterRequest.getUuid(), filterRequest.getBirthDay(), filterRequest.getBreed());
        kafkaTemplate.send("cat-filter-response", filteredCats);  // Отправляем ответ
    }

    @KafkaListener(topics = "cat-add-friend", groupId = "cat_group")
    public void processAddFriend(FriendRequest friendRequest, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        catService.addFriend(friendRequest.getCatUuid(), friendRequest.getCatFriendUuid());
        kafkaTemplate.send("cat-add-friend-response", "Friend added successfully");
    }
}
