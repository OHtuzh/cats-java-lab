package com.example.cat.exceptions;


public class CatNotFoundException extends RuntimeException {

    public CatNotFoundException(Integer catId) {
        super("Кот с ID " + catId + " не был найден.");
    }

}
