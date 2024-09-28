package com.example.http.controllers;

import com.example.http.requests.JwtCreateCatOwnerRequest;
import com.example.http.requests.JwtRequest;
import com.example.http.requests.JwtResponse;
import com.example.http.services.JwtAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControllerImpl {
    private final JwtAuthService jwtAuthService;
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody JwtCreateCatOwnerRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Invalid request");
        }
        return ResponseEntity.ok(jwtAuthService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest request) {
        return ResponseEntity.ok(jwtAuthService.authenticate(request));
    }
}
