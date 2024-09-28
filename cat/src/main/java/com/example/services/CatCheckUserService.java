package com.example.services;



import com.example.dto.CatDto;
import com.example.models.Color;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CatCheckUserService {
    /**
     * Double DAO creation method
     * @param name string
     * @param birthDay LocalDate
     * @param breed string
     * @return CatDto
     * @see CatDto
     */
    CatDto createCatCheckUser(String name, Integer catOwnerUuid, LocalDate birthDay, String breed, Color catColor, Integer ownerId);
    /**
     * Double DAO reading method
     * @param catUuid id to read
     * @return CatDto
     * @see CatDto
     */
    CatDto readCatCheckUser(Integer catUuid, Integer ownerId);
    /**
     * Double DAO deletion method
     * @param catUuid id to delete
     * @see CatDto
     */
    void deleteCatCheckUser(Integer catUuid, Integer ownerId);
    List<CatDto> getByParamsCheckUser(List<String> name, List<Integer> uuid, List<LocalDate> birthDay, List<String> breed, Integer ownerId);
    void addFriendCheckUser(Integer leftCatUuid, Integer rightCatUuid, Integer ownerId);
}
