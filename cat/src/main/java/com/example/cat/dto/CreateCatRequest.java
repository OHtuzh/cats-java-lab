package com.example.cat.dto;

import com.example.cat.models.Color;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class CreateCatRequest {
    @NotNull
    private String name;
    private Integer ownerId;
    @NotNull
    private LocalDate birthday;
    @NotNull
    private String breed;
    @NotNull
    private Color color;
}
