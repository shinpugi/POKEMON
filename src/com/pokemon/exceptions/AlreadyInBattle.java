package com.pokemon.exceptions;

public class AlreadyInBattle extends Exception {
    public AlreadyInBattle(String message) {
        super(message);
    }
}