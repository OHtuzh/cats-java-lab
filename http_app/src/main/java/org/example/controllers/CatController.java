package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dto.CatDto;
import org.example.entities.cat.Color;
import org.example.requests.CreateCatRequest;
import org.example.services.CommonService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/cats")
@RestController
public class CatController {
    private final CommonService commonService;

    @PostMapping
    public CatDto createCat(@RequestBody CreateCatRequest createCatRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Request creation exception");
        }
        return commonService.createCat(createCatRequest.getName(), createCatRequest.getOwnerId(), createCatRequest.getBirthday(), createCatRequest.getBreed(), createCatRequest.getColor());
    }

    @GetMapping("/{id}")
    public CatDto getCat(@PathVariable("id") Integer catId) {
        return commonService.getCat(catId);
    }

    @DeleteMapping("/{id}")
    public void deleteCat(@PathVariable("id") Integer catId) {
        commonService.deleteCat(catId);
    }

    @GetMapping
    public List<CatDto> getFilteredCats(@RequestParam(defaultValue = "") List<String> name,
                                        @RequestParam(defaultValue = "") List<Integer> id,
                                        @RequestParam(defaultValue = "") List<LocalDate> birthDay,
                                        @RequestParam(defaultValue = "") List<Color> color,
                                        @RequestParam(defaultValue = "") List<String> breed) {
        return commonService.getByParams(name, id, birthDay, color, breed);
    }

    @PatchMapping("/{id}/{friendId}")
    public void addFriendship(@PathVariable("id") Integer id, @PathVariable("friendId") Integer friendId) {
        commonService.addFriend(id, friendId);
    }

}
