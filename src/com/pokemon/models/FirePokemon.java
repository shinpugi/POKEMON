package com.pokemon.models;
import com.pokemon.enums.PokemonType;
import java.util.List;

public class FirePokemon extends Pokemon {
    public FirePokemon(String n, List<Move> m) { super(n, PokemonType.FIRE, m); }
}