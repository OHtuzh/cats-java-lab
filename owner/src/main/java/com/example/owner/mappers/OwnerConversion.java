package com.example.owner.mappers;

import com.example.owner.dto.OwnerDto;
import com.example.owner.models.Owner;
import lombok.experimental.UtilityClass;


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
