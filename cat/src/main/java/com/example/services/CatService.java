package com.example.services;


import com.example.dto.CatDto;
import com.example.models.Color;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CatService {
    CatDto createCat(String name, Integer ownerId, LocalDate birthday, String breed, Color color);

    CatDto getCat(Integer id);

    void deleteCat(Integer id);

    void addFriend(Integer catId, Integer friendId);

    List<CatDto> getByParams(List<String> name, List<Integer> id, List<LocalDate> birthDay, List<String> breed);
}
