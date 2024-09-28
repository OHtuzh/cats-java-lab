package com.example.owner.services;

import com.example.owner.dto.OwnerDto;
import com.example.owner.exceptions.CatNotFoundException;
import com.example.owner.exceptions.OwnerNotFoundException;
import com.example.owner.mappers.OwnerConversion;
import com.example.owner.models.Owner;
import com.example.owner.models.Cat;
import com.example.owner.repositories.CatRepository;
import com.example.owner.repositories.OwnerRepository;
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
