package com.pokemon.models;
import com.pokemon.enums.PokemonType;
import java.util.List;

public class FightingPokemon extends Pokemon { public FightingPokemon(String n, List<Move> m) { super(n, PokemonType.FIGHTING, m); } }
