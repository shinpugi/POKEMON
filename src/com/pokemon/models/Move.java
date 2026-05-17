package com.pokemon.models;

import com.pokemon.enums.PokemonType;

public class Move {
    private String name;
    private PokemonType type;
    private int basePower;
    private String effect;
    private int maxPP;
    private int currentPP;

    //Constructor for no extra move effect
    public Move(String name, PokemonType type, int basePower) {
        this.name = name;
        this.type = type;
        this.basePower = basePower;
        this.effect = "NONE";
        this.maxPP = 15;
        this.currentPP = maxPP;
    }

    //Constructor for move with extra effect
    public Move(String name, PokemonType type, int basePower, String effect) {
        this.name = name;
        this.type = type;
        this.basePower = basePower;
        this.effect = effect;
        this.maxPP = 15;
        this.currentPP = maxPP;
    }


    //Reduces PP for each move
    public boolean usePp() {
        if (currentPP > 0) {
            currentPP--;
            return true;
        }
        return false;
    }


    //GETTERS
    public void restorePp() { this.currentPP = maxPP; }
    public String getName() { return name; }
    public PokemonType getType() { return type; }
    public int getBasePower() { return basePower; }
    public String getEffect() { return effect; }
    public int getMaxPp() { return maxPP; }
    public int getCurrentPP() { return currentPP; }
}