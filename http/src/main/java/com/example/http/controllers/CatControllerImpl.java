package com.example.http.controllers;

import com.example.http.requests.CreateCatRequest;
import com.example.http.requests.FilterRequest;
import com.example.http.requests.FriendRequest;
import com.example.http.services.KafkaProducerService;
import com.example.http.services.KafkaResponseListener;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RequestMapping("/api/cats")
@RestController
@PreAuthorize("hasAuthority('USER')")
@SecurityRequirement(name = "Bearer Authentication")
public class CatControllerImpl {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaResponseListener kafkaResponseListener;

    @PostMapping
    public CompletableFuture<Object> createCat(@Valid @RequestBody CreateCatRequest createCatRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Invalid request");
        }

        kafkaProducerService.sendMessage("cat-create", createCatRequest);
        return kafkaResponseListener.getResponse("cat-create-response");
    }

    @GetMapping("/{id}")
    public CompletableFuture<Object> readCat(@PathVariable("id") Integer catUuid) {
        kafkaProducerService.sendMessage("cat-read", catUuid);
        return kafkaResponseListener.getResponse("cat-read-response");
    }

    @GetMapping
    public CompletableFuture<Object> getFilteredCats(@RequestParam(defaultValue = "") List<String> name,
                                                           @RequestParam(defaultValue = "") List<Integer> uuid,
                                                           @RequestParam(defaultValue = "") List<LocalDate> birthDay,
                                                           @RequestParam(defaultValue = "") List<String> breed) {
        kafkaProducerService.sendMessage("cat-filter", new FilterRequest(name, uuid, birthDay, breed));
        return kafkaResponseListener.getResponse("cat-filter-response");
    }

    @PatchMapping("/{id}/{friendId}")
    public CompletableFuture<Object> addCatFriend(@PathVariable("id") Integer catUuid, @PathVariable("friendId") Integer catFriendUuid) {
        kafkaProducerService.sendMessage("cat-add-friend", new FriendRequest(catUuid, catFriendUuid));
        return kafkaResponseListener.getResponse("cat-add-friend-response");
    }
}
