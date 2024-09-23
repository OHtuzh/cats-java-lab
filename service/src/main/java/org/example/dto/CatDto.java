package org.example.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import org.example.entities.cat.Color;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CatDto {
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Exclude
    private Integer id;

    @Setter(AccessLevel.NONE)
    private String name;

    @Setter(AccessLevel.NONE)
    private String breed;

    @Setter(AccessLevel.NONE)
    private LocalDate birthday;

    @Setter(AccessLevel.NONE)
    private Integer ownerId;

    @Setter(AccessLevel.NONE)
    private Color color;

    @Setter(AccessLevel.NONE)
    private List<Integer> friendsIds;

    public List<Integer> friendsIds() {
        return Collections.unmodifiableList(friendsIds);
    }
}
