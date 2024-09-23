package org.example.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.CatDto;
import org.example.entities.cat.Color;
import org.example.requests.CreateCatRequest;
import org.example.services.CommonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/cats")
@RestController
@PreAuthorize("hasAuthority('USER')")
@SecurityRequirement(name = "Bearer Authentication")
public class CatControllerImpl {
    private final CommonService commonService;
    @PostMapping
    public CatDto createCat(@Valid @RequestBody CreateCatRequest createCatRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Invalid request");
        }
        return commonService.createCatCheckUser(createCatRequest.getName(), commonService.getCurrentOwner().getId(), createCatRequest.getBirthday(), createCatRequest.getBreed(), createCatRequest.getColor());
    }

    @GetMapping("/{id}")
    public CatDto readCat(@PathVariable("id") Integer catUuid) {
        return commonService.readCatCheckUser(catUuid);
    }

    @GetMapping
    public List<CatDto> getFilteredCats(@RequestParam(defaultValue = "") List<String> name,
                                        @RequestParam(defaultValue = "") List<Integer> uuid,
                                        @RequestParam(defaultValue = "") List<LocalDate> birthDay,
                                        @RequestParam(defaultValue = "") List<Color> color,
                                        @RequestParam(defaultValue = "") List<String> breed) {
        return commonService.getByParamsCheckUser(name, uuid, birthDay, color, breed);
    }
    @PatchMapping("/{id}/{friendId}")
    public void addCatFriend(@PathVariable("id") Integer catUuid, @PathVariable("friendId") Integer catFriendUuid) {
        commonService.addFriendCheckUser(catUuid, catFriendUuid);
    }
}
