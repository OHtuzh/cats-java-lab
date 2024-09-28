package com.example.http.controllers;


import com.example.http.services.JwtAuthServiceImpl;
import com.example.http.services.KafkaProducerService;
import com.example.http.services.KafkaResponseListener;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.security.web.authentication.preauth.j2ee.J2eeBasedPreAuthenticatedWebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<Object> readCatOwner() {
        kafkaProducerService.sendMessage("owner-get-current", jwtAuthService.getCurrentOwner().getId());
        return kafkaResponseListener.getResponse("owner-get-current-response");
    }
}
