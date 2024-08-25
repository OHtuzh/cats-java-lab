package org.example.services;

import org.example.dao.CatDao;
import org.example.models.cat.Cat;

import java.util.List;

public class CatService {
    private final CatDao catDao;

    public CatService(CatDao catDao) {
        this.catDao = catDao;
    }

    public Cat findCat(int id) {
        return catDao.findById(id);
    }

    public void saveCat(Cat cat) {
        catDao.save(cat);
    }

    public void deleteCat(Cat cat) {
        catDao.delete(cat);
    }

    public void updateCat(Cat cat) {
        catDao.update(cat);
    }

    public List<Cat> findAllCats() {
        return catDao.findAll();
    }
}
