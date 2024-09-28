package com.example.owner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CreateOwnerRequest {
    @NonNull
    private String name;
    @NonNull
    private LocalDate birthday;
}
