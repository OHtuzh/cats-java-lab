package com.example.http.model;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class UserDto {
    Integer uuid;
    String username;
    Integer catOwnerUuid;
    Set<Roles> roles;
}
