package com.pokemon.enums;

public enum PokemonType {
    FIRE, WATER, GRASS, NORMAL, GHOST, PSYCHIC, FIGHTING, DRAGON, BUG, ROCK, STEEL;

    //Computes multiplier for the final damage based on the type effectivity
    public double getEffectiveness(PokemonType defender) {

        if (this == FIRE && (defender == GRASS || defender == BUG || defender == STEEL)) return 2.0;
        if (this == FIRE && (defender == WATER || defender == ROCK || defender == DRAGON)) return 0.5;

        if (this == WATER && (defender == FIRE || defender == ROCK)) return 2.0;
        if (this == WATER && (defender == GRASS || defender == DRAGON)) return 0.5;

        if (this == GRASS && (defender == WATER || defender == ROCK)) return 2.0;
        if (this == GRASS && (defender == FIRE || defender == BUG || defender == DRAGON)) return 0.5;

        if (this == FIGHTING && (defender == ROCK || defender == STEEL || defender == NORMAL)) return 2.0;
        if (this == FIGHTING && (defender == GHOST)) return 0.0;

        if (this == GHOST && (defender == GHOST || defender == PSYCHIC)) return 2.0;
        if (this == GHOST && (defender == NORMAL)) return 0.0;

        if (this == PSYCHIC && (defender == FIGHTING)) return 2.0;
        if (this == PSYCHIC && (defender == STEEL)) return 0.5;

        if (this == DRAGON && (defender == DRAGON)) return 2.0;
        if (this == DRAGON && (defender == STEEL)) return 0.5;

        return 1.0;
    }
}