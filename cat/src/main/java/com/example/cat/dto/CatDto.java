package com.example.cat.dto;

import com.example.cat.models.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
public class CatDto {
    @EqualsAndHashCode.Exclude
    private Integer id;

    private String name;

    private String breed;

    private LocalDate birthday;

    private Integer ownerId;

    private Color color;

    private List<Integer> friendsIds;

    public List<Integer> friendsIds() {
        return Collections.unmodifiableList(friendsIds);
    }
}
