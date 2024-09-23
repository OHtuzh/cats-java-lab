package org.example.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.example.model.Roles;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class JwtCreateCatOwnerRequest extends JwtRequest {
    @Setter(AccessLevel.NONE)
    private final Roles defaultRole = Roles.valueOf("USER");
    @NotNull(message = "CatOwner birthday should not be null")
    @PastOrPresent(message = "Data should be in past or present")
    private LocalDate birthDate;
}
