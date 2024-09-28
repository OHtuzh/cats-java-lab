package com.example.controllers;


import com.example.requests.CreateCatRequest;
import com.example.requests.FilterRequest;
import com.example.requests.FriendRequest;
import com.example.services.JwtAuthServiceImpl;
import com.example.services.KafkaProducerService;
import com.example.services.KafkaResponseListener;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/admin/cats")
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminCatControllerImpl {
    private final KafkaProducerService kafkaProducerService;
    private final KafkaResponseListener kafkaResponseListener;
    private final JwtAuthServiceImpl jwtAuthService;

    @PostMapping
    public String createCat(@Valid @RequestBody CreateCatRequest createCatRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Validation error");
        }
        createCatRequest.setId(jwtAuthService.getCurrentOwner().getId());
        kafkaProducerService.sendMessage("cat-create", createCatRequest);
        return kafkaResponseListener.getResponse("cat-create-response").join().toString();
    }

    @GetMapping("/{id}")
    public String readCat(@PathVariable("id") Integer catUuid) {
        kafkaProducerService.sendMessage("cat-read", catUuid);
        return kafkaResponseListener.getResponse("cat-read-response").join().toString();
    }

    @DeleteMapping("/{id}")
    public String deleteCat(@PathVariable("id") Integer catUuid) {
        kafkaProducerService.sendMessage("cat-delete", catUuid);
        return kafkaResponseListener.getResponse("cat-delete-response").join().toString();
    }

    @GetMapping
    public String getFilteredCats(@RequestParam(defaultValue = "") List<String> name,
                                                           @RequestParam(defaultValue = "") List<Integer> uuid,
                                                           @RequestParam(defaultValue = "") List<LocalDate> birthDay,
                                                           @RequestParam(defaultValue = "") List<String> breed) {
        kafkaProducerService.sendMessage("cat-filter", new FilterRequest(name, uuid, birthDay, breed));
        return kafkaResponseListener.getResponse("cat-filter-response").join().toString();
    }

    @PatchMapping("/{id}/{friendId}")
    public String addCatFriend(@PathVariable("id") Integer catUuid, @PathVariable("friendId") Integer catFriendUuid) {
        kafkaProducerService.sendMessage("cat-add-friend", new FriendRequest(catUuid, catFriendUuid));
        return kafkaResponseListener.getResponse("cat-add-friend-response").join().toString();
    }


}
