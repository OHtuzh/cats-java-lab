package com.example.services;

import com.example.dto.CatDto;
import com.example.exceptions.CatNotFoundException;
import com.example.mappers.CatConversion;
import com.example.models.Cat;
import com.example.models.Color;
import com.example.repositories.CatRepository;
import com.example.repositories.OwnerRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CatServiceImpl implements CatService {

    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public CatDto createCat(String name, Integer ownerId, LocalDate birthday, String breed, Color color) {
        Cat cat = new Cat(name, birthday, breed, color);
        cat.setOwner(ownerRepository.findById(ownerId).orElseThrow());  //TODO: Change to OwnerId
        catRepository.save(cat);
        return CatConversion.castToCatDto(cat);
    }

    @Override
    public CatDto getCat(Integer id) {
        return CatConversion.castToCatDto(getCatById(id));
    }

    @Override
    public void deleteCat(Integer id) {
        Cat catToDelete = getCatById(id);
        catToDelete.getFriends().forEach(catToDelete::removeFriend);
        catRepository.delete(catToDelete);
    }

    @Override
    public void addFriend(Integer catId, Integer friendId) {
        Cat cat = getCatById(catId);
        Cat friend = getCatById(friendId);
        cat.addFriend(friend);
        catRepository.save(cat);
    }

    @Override
    public List<CatDto> getByParams(List<String> name, List<Integer> id, List<LocalDate> birthDay, List<String> breed) {
        Specification<Cat> spec = Specification.where(null);
        spec = spec.or((root, query, criteriaBuilder) -> root.get("id").in(id));
        spec = spec.or((root, query, criteriaBuilder) -> root.get("name").in(name));
        spec = spec.or((root, query, criteriaBuilder) -> root.get("birthday").in(birthDay));
        spec = spec.or((root, query, criteriaBuilder) -> root.get("breed").in(breed));

        return catRepository.findAll(spec).stream().map(CatConversion::castToCatDto).toList();
    }

    private Cat getCatById(Integer id) {
        return catRepository.findById(id).orElseThrow(() -> new CatNotFoundException(id));
    }

}
