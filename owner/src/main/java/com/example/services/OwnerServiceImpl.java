package com.example.services;

import com.example.exceptions.OwnerNotFoundException;
import com.example.dto.OwnerDto;
import com.example.exceptions.CatNotFoundException;
import com.example.mappers.OwnerConversion;
import com.example.models.Owner;
import com.example.models.Cat;
import com.example.repositories.CatRepository;
import com.example.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CatRepository catRepository;


    @Override
    public OwnerDto createOwner(String name, LocalDate birthday) {
        Owner owner = ownerRepository.save(new Owner(name, birthday));
        return OwnerConversion.castToOwnerDto(owner);
    }

    @Override
    public OwnerDto getOwner(Integer id) {
        return OwnerConversion.castToOwnerDto(getOwnerById(id));
    }

    @Override
    public void deleteOwner(Integer id) {
        Owner ownerToDelete = getOwnerById(id);
        ownerRepository.delete(ownerToDelete);
    }

    @Override
    public void addCat(Integer id, Integer catId) {
        Owner owner = getOwnerById(id);
        Cat cat = getCatById(catId);
        owner.addCat(cat);
        catRepository.save(cat);
    }

    public OwnerDto getCurrentOwner(Integer id) {
        return OwnerConversion.castToOwnerDto(getOwnerById(id));
    }


    private Owner getOwnerById(Integer ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(ownerId));
    }

    private Cat getCatById(Integer id) {
        return catRepository.findById(id).orElseThrow(() -> new CatNotFoundException(id));
    }
}
