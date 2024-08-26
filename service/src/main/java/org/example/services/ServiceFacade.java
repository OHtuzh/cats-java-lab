package org.example.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import org.example.conversion.CatConversion;
import org.example.conversion.OwnerConversion;
import org.example.dao.CatRepository;
import org.example.dao.OwnerRepository;
import org.example.dto.CatDto;
import org.example.dto.OwnerDto;
import org.example.entities.cat.Cat;
import org.example.entities.cat.Color;
import org.example.entities.owner.Owner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@ExtensionMethod({CatConversion.class, OwnerConversion.class})
public class ServiceFacade implements CommonService {
    private final CatRepository catRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public CatDto createCat(String name, Integer ownerId, LocalDate birthday, String breed, Color color) {
        Cat cat = catRepository.save(new Cat(name, birthday, breed, color));
        addCat(ownerId, cat.getId());
        return cat.castToCatDto();
    }

    @Override
    public CatDto getCat(Integer id) {
        return getCatById(id).castToCatDto();
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
        cat.addFriend(friend);
    }

    @Override
    public List<CatDto> getByParams(List<String> name, List<Integer> id, List<LocalDate> birthDay, List<Color> color, List<String> breed) {
        Specification<Cat> spec = Specification.where(null);
        spec = spec.or((root, query, criteriaBuilder) -> root.get("id").in(id));
        spec = spec.or((root, query, criteriaBuilder) -> root.get("name").in(name));
        spec = spec.or((root, query, criteriaBuilder) -> root.get("birthday").in(birthDay));
        spec = spec.or((root, query, criteriaBuilder) -> root.get("color").in(color));
        spec = spec.or((root, query, criteriaBuilder) -> root.get("breed").in(breed));

        return catRepository.findAll(spec).stream().map(cat -> cat.castToCatDto()).toList();
    }

    @Override
    public OwnerDto createOwner(String name, LocalDate birthday) {
        Owner owner = ownerRepository.save(new Owner(name, birthday));
        return owner.castToOwnerDto();
    }

    @Override
    public OwnerDto getOwner(Integer id) {
        return getOwnerById(id).castToOwnerDto();
    }

    @Override
    public void deleteOwner(Integer id) {
        Owner ownerToDelete = getOwnerById(id);
        ownerToDelete.getCats().stream().map(Cat::getId).forEach(this::deleteCat);
        ownerRepository.delete(ownerToDelete);
    }

    @Override
    public void addCat(Integer id, Integer catId) {
        Owner owner = getOwnerById(id);
        Cat cat = getCatById(catId);
        owner.addCat(cat);
        catRepository.save(cat);
    }

    private Cat getCatById(Integer catId) {
        return catRepository.findById(catId).orElseThrow(() -> new RuntimeException("Cannot find cat"));
    }

    private Owner getOwnerById(Integer ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Cannot find owner"));
    }
}
