package com.example.requests;

import com.example.models.Color;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
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
    private Integer id;
}
