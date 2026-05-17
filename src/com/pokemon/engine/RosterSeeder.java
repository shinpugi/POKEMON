package com.pokemon.engine;

import com.pokemon.enums.PokemonType;
import com.pokemon.models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Pokemon Generator

public class RosterSeeder {
    public static List<Pokemon> generatePool() {
        List<Pokemon> pool = new ArrayList<>();

        //Type, Name, MoveSet, MaxHealth
        pool.add(new FirePokemon("Charizard", Arrays.asList(new Move("Flamethrower", PokemonType.FIRE, 35), new Move("Dragon Claw", PokemonType.DRAGON, 30), new Move("Slash", PokemonType.NORMAL, 25), new Move("Fire Spin", PokemonType.FIRE, 15))).setMaxHealth(296));
        pool.add(new WaterPokemon("Blastoise", Arrays.asList(new Move("Hydro Pump", PokemonType.WATER, 40), new Move("Bite", PokemonType.NORMAL, 20), new Move("Water Gun", PokemonType.WATER, 15), new Move("Tackle", PokemonType.NORMAL, 15))).setMaxHealth(300));
        pool.add(new GrassPokemon("Venusaur", Arrays.asList(new Move("Solar Beam", PokemonType.GRASS, 45), new Move("Razor Leaf", PokemonType.GRASS, 20), new Move("Tackle", PokemonType.NORMAL, 15), new Move("Vine Whip", PokemonType.GRASS, 15))).setMaxHealth(302));
        pool.add(new GhostPokemon("Gengar", Arrays.asList(new Move("Shadow Ball", PokemonType.GHOST, 30), new Move("Lick", PokemonType.GHOST, 15), new Move("Night Shade", PokemonType.GHOST, 20), new Move("Punch", PokemonType.NORMAL, 15))).setMaxHealth(260));
        pool.add(new PsychicPokemon("Alakazam", Arrays.asList(new Move("Psychic", PokemonType.PSYCHIC, 35), new Move("Confusion", PokemonType.PSYCHIC, 20), new Move("Psybeam", PokemonType.PSYCHIC, 25), new Move("Headbutt", PokemonType.NORMAL, 25))).setMaxHealth(210));
        pool.add(new FightingPokemon("Machamp", Arrays.asList(new Move("Cross Chop", PokemonType.FIGHTING, 35), new Move("Karate Chop", PokemonType.FIGHTING, 20), new Move("Submission", PokemonType.FIGHTING, 30, "RECOIL"), new Move("Tackle", PokemonType.NORMAL, 15))).setMaxHealth(320));
        pool.add(new DragonPokemon("Dragonite", Arrays.asList(new Move("Outrage", PokemonType.DRAGON, 45), new Move("Dragon Claw", PokemonType.DRAGON, 30), new Move("Twister", PokemonType.DRAGON, 15), new Move("Slam", PokemonType.NORMAL, 30))).setMaxHealth(322));
        pool.add(new FirePokemon("Arcanine", Arrays.asList(new Move("Fire Fang", PokemonType.FIRE, 25), new Move("Flamethrower", PokemonType.FIRE, 35), new Move("Bite", PokemonType.NORMAL, 20), new Move("Take Down", PokemonType.NORMAL, 35, "RECOIL"))).setMaxHealth(320));
        pool.add(new WaterPokemon("Gyarados", Arrays.asList(new Move("Aqua Tail", PokemonType.WATER, 35), new Move("Hydro Pump", PokemonType.WATER, 40), new Move("Bite", PokemonType.NORMAL, 20), new Move("Twister", PokemonType.DRAGON, 15))).setMaxHealth(330));
        pool.add(new FirePokemon("Typhlosion", Arrays.asList(new Move("Eruption", PokemonType.FIRE, 50), new Move("Lava Plume", PokemonType.FIRE, 30), new Move("Swift", PokemonType.NORMAL, 20), new Move("Rollout", PokemonType.ROCK, 15))).setMaxHealth(296));
        pool.add(new WaterPokemon("Feraligatr", Arrays.asList(new Move("Aqua Tail", PokemonType.WATER, 35), new Move("Crunch", PokemonType.NORMAL, 30), new Move("Water Gun", PokemonType.WATER, 15), new Move("Slash", PokemonType.NORMAL, 25))).setMaxHealth(310));
        pool.add(new GrassPokemon("Meganium", Arrays.asList(new Move("Petal Dance", PokemonType.GRASS, 45), new Move("Magical Leaf", PokemonType.GRASS, 20), new Move("Razor Leaf", PokemonType.GRASS, 20), new Move("Body Slam", PokemonType.NORMAL, 30))).setMaxHealth(300));
        pool.add(new BugPokemon("Scizor", Arrays.asList(new Move("X-Scissor", PokemonType.BUG, 30), new Move("Fury Cutter", PokemonType.BUG, 15, "MULTI_HIT"), new Move("Slash", PokemonType.NORMAL, 25), new Move("Metal Claw", PokemonType.STEEL, 20))).setMaxHealth(280));
        pool.add(new RockPokemon("Tyranitar", Arrays.asList(new Move("Stone Edge", PokemonType.ROCK, 35), new Move("Rock Slide", PokemonType.ROCK, 25), new Move("Crunch", PokemonType.NORMAL, 30), new Move("Earthquake", PokemonType.NORMAL, 35))).setMaxHealth(340));
        pool.add(new SteelPokemon("Steelix", Arrays.asList(new Move("Iron Tail", PokemonType.STEEL, 35), new Move("Flash Cannon", PokemonType.STEEL, 30), new Move("Rock Throw", PokemonType.ROCK, 20), new Move("Slam", PokemonType.NORMAL, 30))).setMaxHealth(290));
        pool.add(new GrassPokemon("Sceptile", Arrays.asList(new Move("Leaf Blade", PokemonType.GRASS, 35), new Move("Giga Drain", PokemonType.GRASS, 25, "DRAIN"), new Move("Quick Attack", PokemonType.NORMAL, 15), new Move("Slam", PokemonType.NORMAL, 30))).setMaxHealth(280));
        pool.add(new FirePokemon("Blaziken", Arrays.asList(new Move("Blaze Kick", PokemonType.FIRE, 30), new Move("Fire Punch", PokemonType.FIRE, 25), new Move("Double Kick", PokemonType.FIGHTING, 15, "MULTI_HIT"), new Move("Slash", PokemonType.NORMAL, 25))).setMaxHealth(300));
        pool.add(new WaterPokemon("Swampert", Arrays.asList(new Move("Muddy Water", PokemonType.WATER, 35), new Move("Surf", PokemonType.WATER, 35), new Move("Tackle", PokemonType.NORMAL, 15), new Move("Take Down", PokemonType.NORMAL, 35, "RECOIL"))).setMaxHealth(340));
        pool.add(new PsychicPokemon("Gardevoir", Arrays.asList(new Move("Psychic", PokemonType.PSYCHIC, 35), new Move("Confusion", PokemonType.PSYCHIC, 20), new Move("Magical Leaf", PokemonType.GRASS, 20), new Move("Headbutt", PokemonType.NORMAL, 25))).setMaxHealth(276));
        pool.add(new SteelPokemon("Metagross", Arrays.asList(new Move("Meteor Mash", PokemonType.STEEL, 35), new Move("Bullet Punch", PokemonType.STEEL, 15), new Move("Confusion", PokemonType.PSYCHIC, 20), new Move("Zen Headbutt", PokemonType.PSYCHIC, 30))).setMaxHealth(300));

        return pool;
    }
}