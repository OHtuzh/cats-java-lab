package com.example.controllers;



import com.example.requests.CreateOwnerRequest;
import com.example.services.KafkaProducerService;
import com.example.services.KafkaResponseListener;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/admin/cat-owners")
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminCatOwnerControllerImpl {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaResponseListener kafkaResponseListener;

    @PostMapping
    public String createCatOwner(@Valid @RequestBody CreateOwnerRequest createCatOwnerRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Validation error");
        }

        kafkaProducerService.sendMessage("owner-create", createCatOwnerRequest);
        return kafkaResponseListener.getResponse("owner-create-response").join().toString();
    }

    @GetMapping("/{id}")
    public String readCatOwner(@PathVariable("id") Integer catOwnerUuid) {
        kafkaProducerService.sendMessage("owner-read", catOwnerUuid);
        return kafkaResponseListener.getResponse("owner-read-response").join().toString();
    }

    @DeleteMapping("/{id}")
    public String deleteCatOwner(@PathVariable("id") Integer catOwnerUuid) {
        kafkaProducerService.sendMessage("owner-delete", catOwnerUuid);
        return kafkaResponseListener.getResponse("owner-delete-response").join().toString();
    }
}
