package org.example.services;

import org.example.requests.JwtCreateCatOwnerRequest;
import org.example.requests.JwtRequest;
import org.example.responses.JwtResponse;

public interface JwtAuthService {
    JwtResponse authenticate(JwtRequest jwtRequest);
    JwtResponse register(JwtCreateCatOwnerRequest jwtCreateCatOwnerRequest);
}
