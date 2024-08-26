package org.example.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entities.cat.Color;

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
