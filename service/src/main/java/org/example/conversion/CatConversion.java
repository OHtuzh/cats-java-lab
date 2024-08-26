package org.example.conversion;

import lombok.experimental.UtilityClass;
import org.example.dto.CatDto;
import org.example.entities.cat.Cat;

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
