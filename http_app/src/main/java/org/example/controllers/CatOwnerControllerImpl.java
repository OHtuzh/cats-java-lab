package org.example.controllers;

import org.example.dto.OwnerDto;
import org.example.services.CommonService;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/cat-owners")
@RestController
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@SecurityRequirement(name = "Bearer Authentication")
public class CatOwnerControllerImpl {
    private final CommonService commonService;
    @GetMapping("/self")
    public OwnerDto readCatOwner() {
        return commonService.getCurrentOwner();
    }
}