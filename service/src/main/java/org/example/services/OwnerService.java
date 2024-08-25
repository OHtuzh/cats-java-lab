package org.example.services;

import org.example.dao.OwnerDao;
import org.example.models.owner.Owner;

import java.util.List;

public class OwnerService {
    private final OwnerDao ownerDao;

    public OwnerService(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public Owner findOwner(int id) {
        return ownerDao.findById(id);
    }

    public void saveOwner(Owner owner) {
        ownerDao.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownerDao.delete(owner);
    }

    public void updateOwner(Owner owner) {
        ownerDao.update(owner);
    }

    public List<Owner> findAllOwners() {
        return ownerDao.findAll();
    }
}
