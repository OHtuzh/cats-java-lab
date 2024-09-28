package com.example.http.services;


import com.example.http.requests.JwtCreateCatOwnerRequest;
import com.example.http.requests.JwtRequest;
import com.example.http.requests.JwtResponse;

public interface JwtAuthService {
    JwtResponse authenticate(JwtRequest jwtRequest);
    JwtResponse register(JwtCreateCatOwnerRequest jwtCreateCatOwnerRequest);
}
