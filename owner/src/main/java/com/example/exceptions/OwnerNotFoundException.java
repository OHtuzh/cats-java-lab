package com.example.exceptions;


public class OwnerNotFoundException extends RuntimeException {

    public OwnerNotFoundException(Integer ownerId) {
        super("Owner с ID " + ownerId + " не был найден.");
    }

}
