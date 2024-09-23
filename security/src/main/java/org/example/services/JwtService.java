package org.example.services;

import io.jsonwebtoken.Claims;
import org.example.model.UserDto;

import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDto user);
    boolean isTokenValid(String token);
    boolean isTokenExpired(String token);
}
