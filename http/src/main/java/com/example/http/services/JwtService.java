package com.example.http.services;

import com.example.http.model.UserDto;
import io.jsonwebtoken.Claims;

import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDto user);
    boolean isTokenValid(String token);
    boolean isTokenExpired(String token);
}
