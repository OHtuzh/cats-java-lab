package com.example.cat.mappers;

import com.example.cat.dto.CatDto;
import com.example.cat.models.Cat;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CatConversion {
    public static CatDto castToCatDto(Cat cat) {
        if (cat == null) {
            return new CatDto(null, null, null, null, null, null, null);
        }
        return new CatDto(
                cat.getId(),
                cat.getName(),
                cat.getBreed(),
                cat.getBirthday(),
                (cat.getOwner() != null) ? cat.getOwner().getId() : null,
                cat.getColor(),
                cat.getFriends().stream().map(Cat::getId).toList()
        );
    }
}
