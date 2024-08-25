package org.example.controllers;

import org.example.models.cat.Cat;
import org.example.services.CatService;

import java.util.List;

public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    public Cat findCat(int id) {
        return catService.findCat(id);
    }

    public void saveCat(Cat cat) {
        catService.saveCat(cat);
    }

    public void deleteCat(Cat cat) {
        catService.deleteCat(cat);
    }

    public void updateCat(Cat cat) {
        catService.updateCat(cat);
    }

    public List<Cat> findAllCats() {
        return catService.findAllCats();
    }
}
