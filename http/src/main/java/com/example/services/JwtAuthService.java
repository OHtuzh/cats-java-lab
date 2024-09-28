package com.example.services;


import com.example.requests.JwtCreateCatOwnerRequest;
import com.example.requests.JwtRequest;
import com.example.requests.JwtResponse;

public interface JwtAuthService {
    JwtResponse authenticate(JwtRequest jwtRequest);
    JwtResponse register(JwtCreateCatOwnerRequest jwtCreateCatOwnerRequest);
}
