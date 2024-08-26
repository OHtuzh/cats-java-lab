package org.example.services;

import org.example.dto.OwnerDto;

import java.time.LocalDate;

public interface OwnerService {
    OwnerDto createOwner(String name, LocalDate birthday);

    OwnerDto getOwner(Integer id);

    void deleteOwner(Integer id);

    void addCat(Integer id, Integer catId);
}
