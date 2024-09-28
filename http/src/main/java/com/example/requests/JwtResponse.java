package com.example.requests;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class JwtResponse {
    String type = "Bearer";
    String token;
}
