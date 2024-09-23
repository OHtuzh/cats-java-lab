package org.example.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.dto.OwnerDto;
import org.example.requests.CreateOwnerRequest;
import org.example.services.CommonService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/admin/cat-owners")
@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@SecurityRequirement(name = "Bearer Authentication")
public class AdminCatOwnerControllerImpl {
    private final CommonService commonService;
    @PostMapping
    public OwnerDto createCatOwner(@Valid @RequestBody CreateOwnerRequest createCatOwnerRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("Validation error");
        }
        return commonService.createOwner(createCatOwnerRequest.getName(), createCatOwnerRequest.getBirthday());
    }
    @GetMapping("/{id}")
    public OwnerDto readCatOwner(@PathVariable("id") Integer catOwnerUuid) {
        return commonService.getOwner(catOwnerUuid);
    }
    @DeleteMapping("/{id}")
    public void deleteCatOwner(@PathVariable("id") Integer catOwnerUuid) {
        commonService.deleteOwner(catOwnerUuid);
    }
}
