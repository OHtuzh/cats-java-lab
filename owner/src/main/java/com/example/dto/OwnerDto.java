package com.example.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OwnerDto {
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer id;

    @Setter(AccessLevel.NONE)
    private String name;

    @Setter(AccessLevel.NONE)
    private LocalDate birthday;

    @Setter(AccessLevel.NONE)
    private List<CatDto> cats;

    public List<CatDto> getCats() {
        return Collections.unmodifiableList(cats);
    }
}
