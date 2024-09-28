package com.example.controllers;

import com.example.services.JwtAuthServiceImpl;
import com.example.services.KafkaProducerService;
import com.example.services.KafkaResponseListener;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/cat-owners")
@RestController
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@SecurityRequirement(name = "Bearer Authentication")
public class CatOwnerControllerImpl {

    private final KafkaProducerService kafkaProducerService;
    private final KafkaResponseListener kafkaResponseListener;
    private final JwtAuthServiceImpl jwtAuthService;

    @GetMapping("/self")
    public String readCatOwner() {
        kafkaProducerService.sendMessage("owner-get-current", jwtAuthService.getCurrentOwner().getId());

        return kafkaResponseListener.getResponse("owner-get-current-response").join().toString();
    }
}
