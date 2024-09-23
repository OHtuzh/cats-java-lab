package org.example.services;

import org.example.dto.CatDto;
import org.example.entities.cat.Color;

import java.time.LocalDate;
import java.util.List;

public interface CatService {
    CatDto createCat(String name, Integer ownerId, LocalDate birthday, String breed, Color color);

    CatDto getCat(Integer id);

    void deleteCat(Integer id);

    void addFriend(Integer catId, Integer friendId);

    List<CatDto> getByParams(List<String> name, List<Integer> id, List<LocalDate> birthDay, List<Color> color, List<String> breed);
}
