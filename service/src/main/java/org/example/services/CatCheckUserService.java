package org.example.services;

import org.example.dto.CatDto;
import org.example.entities.cat.Color;

import java.time.LocalDate;
import java.util.List;

public interface CatCheckUserService {
    /**
     * Double DAO creation method
     * @param name string
     * @param birthDay LocalDate
     * @param breed string
     * @param catColor Color
     * @return CatDto
     * @see CatDto
     */
    CatDto createCatCheckUser(String name, Integer catOwnerUuid, LocalDate birthDay, String breed, Color catColor);
    /**
     * Double DAO reading method
     * @param catUuid id to read
     * @return CatDto
     * @see CatDto
     */
    CatDto readCatCheckUser(Integer catUuid);
    /**
     * Double DAO deletion method
     * @param catUuid id to delete
     * @see CatDto
     */
    void deleteCatCheckUser(Integer catUuid);
    List<CatDto> getByParamsCheckUser(List<String> name, List<Integer> uuid, List<LocalDate> birthDay, List<Color> color, List<String> breed);
}
