package com.example.services;

import com.example.dto.CatDto;
import com.example.exceptions.OwnerNotFoundException;
import com.example.models.Color;
import com.example.models.Cat;
import com.example.models.Owner;
import com.example.repositories.CatRepository;
import com.example.repositories.OwnerRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CatCheckUserServiceImpl implements CatCheckUserService {
    private final CatService catService;
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Value("${application.security.jwt.secret-key}")
    private String jwtSecret;

    public CatCheckUserServiceImpl(CatService catService, CatRepository catRepository, OwnerRepository ownerRepository) {
        this.catService = catService;
        this.catRepository = catRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public CatDto createCatCheckUser(String name, Integer catOwnerUuid, LocalDate birthDay, String breed, Color catColor, Integer ownerId) {
        if (!ownerId.equals(catOwnerUuid)) {
            throw new RuntimeException("Not found owner %d".formatted(catOwnerUuid));
        }
        return catService.createCat(name, catOwnerUuid, birthDay, breed, catColor);
    }

    @Override
    public CatDto readCatCheckUser(Integer catUuid, Integer ownerId) {
        if (catRepository.findAllByOwnerId(ownerId)
                .stream()
                .map(Cat::getId)
                .toList()
                .contains(catUuid)) {
            throw new RuntimeException("Not found cat %d".formatted(catUuid));
        }
        return catService.getCat(catUuid);
    }

    @Override
    public void deleteCatCheckUser(Integer catUuid, Integer ownerId) {
        if (catRepository.findAllByOwnerId(ownerId)
                .stream()
                .map(Cat::getId)
                .toList()
                .contains(catUuid)) {
            throw new RuntimeException("Not found cat %d".formatted(catUuid));
        }
        catService.deleteCat(catUuid);
    }

    @Override
    public List<CatDto> getByParamsCheckUser(List<String> name, List<Integer> uuid, List<LocalDate> birthDay, List<String> breed, Integer ownerId) {
        return catService.getByParams(name, uuid, birthDay, breed)
                .stream()
                .filter(catDto -> catDto.getOwnerId().equals(ownerId))
                .toList();
    }

    @Override
    public void addFriendCheckUser(Integer leftCatUuid, Integer rightCatUuid, Integer ownerId) {
        if (catRepository.findAllByOwnerId(getCurrentOwner(ownerId).getId())
                .stream()
                .map(Cat::getId)
                .toList()
                .contains(leftCatUuid)) {
            throw new RuntimeException("Not found cat %d".formatted(leftCatUuid));
        }
        catService.addFriend(leftCatUuid, rightCatUuid);
    }

    public Owner getCurrentOwner(Integer id) {
        return ownerRepository.findById(id).orElseThrow(() -> new OwnerNotFoundException(id));
    }

}
