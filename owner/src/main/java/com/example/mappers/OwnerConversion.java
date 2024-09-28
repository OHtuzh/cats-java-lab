package com.example.mappers;

import com.example.dto.OwnerDto;
import com.example.models.Owner;
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
