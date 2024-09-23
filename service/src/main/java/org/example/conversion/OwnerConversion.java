package org.example.conversion;

import lombok.experimental.UtilityClass;
import org.example.dto.OwnerDto;
import org.example.entities.owner.Owner;

@UtilityClass
public class OwnerConversion {
    public static OwnerDto castToOwnerDto(Owner owner) {
        if (owner == null) {
            return new OwnerDto(null, null, null, null);
        }
        return new OwnerDto(
                owner.getId(),
                owner.getName(),
                owner.getBirthday(),
                owner.getCats().stream().map(CatConversion::castToCatDto).toList()
        );
    }
}
