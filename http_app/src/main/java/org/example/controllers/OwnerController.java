package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.dto.OwnerDto;
import org.example.requests.CreateOwnerRequest;
import org.example.services.CommonService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/owners")
@RestController
public class OwnerController {
    private final CommonService commonService;

    @PostMapping
    public OwnerDto createOwner(@RequestBody CreateOwnerRequest createOwnerRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Validation error");
        }
        return commonService.createOwner(createOwnerRequest.getName(), createOwnerRequest.getBirthday());
    }

    @GetMapping("/{id}")
    public OwnerDto getOwner(@PathVariable("id") Integer id) {
        return commonService.getOwner(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable("id") Integer id) {
        commonService.deleteOwner(id);
    }
}
