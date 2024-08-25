package org.example.controllers;

import org.example.models.owner.Owner;
import org.example.services.OwnerService;

import java.util.List;

public class OwnerController {
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public Owner findOwner(int id) {
        return ownerService.findOwner(id);
    }

    public void saveOwner(Owner owner) {
        ownerService.saveOwner(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerService.deleteOwner(owner);
    }

    public void updateOwner(Owner owner) {
        ownerService.updateOwner(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerService.findAllOwners();
    }
}
