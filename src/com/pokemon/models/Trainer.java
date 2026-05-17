package com.pokemon.models;

import com.pokemon.exceptions.FaintedSelectionException;
import com.pokemon.exceptions.OutOfPotionsException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.pokemon.exceptions.AlreadyInBattle;

public class Trainer {
    private String name;
    private List<Pokemon> team;
    private Map<String, Integer> inventory;
    private Pokemon activePokemon;

    public Trainer(String name) {
        this.name = name;
        this.team = new ArrayList<>();
        this.inventory = new HashMap<>();
        this.inventory.put("Full Restore Potion", 5);
    }

    //Add pokemon to the team
    public void addPokemon(Pokemon p) {
        if (team.size() < 5) team.add(p);
    }

    //Sets active pokemon to avoid choosing fainted or already in battle pokemon
    public void setActivePokemon(int index) throws FaintedSelectionException, AlreadyInBattle {
        Pokemon selected = team.get(index);

        if (selected.isFainted()) {
            throw new FaintedSelectionException(selected.getName() + " is unable to battle!");
        }
        if (selected == this.activePokemon && !selected.isFainted()) {
            throw new AlreadyInBattle(selected.getName() + " is already in battle!");
        }

        this.activePokemon = selected;
    }

    //Uses full restore if you have some
    public void useItem(String itemName) throws OutOfPotionsException {
        int count = inventory.getOrDefault(itemName, 0);
        if (count <= 0) throw new OutOfPotionsException("No " + itemName + "s left!");
        inventory.put(itemName, count - 1);
        activePokemon.fullyRestore();
        System.out.println(name + " used a " + itemName + "! " + activePokemon.getName() + " is fully restored!");
    }

    //Checks for available pokemon to battle
    public boolean hasAvailablePokemon() {
        for (Pokemon p : team) {
            if (!p.isFainted()) return true;
        }
        return false;
    }

    //Checks how many full restores left
    public int getItemCount(String itemName) {
        return inventory.getOrDefault(itemName, 0);
    }

    //Checks if pokemon is in party already
    public boolean hasPokemon(String pokemonName) {
        for (Pokemon p : team) {
            if (p.getName().equals(pokemonName)) {
                return true;
            }
        }
        return false;
    }


    public Pokemon getActivePokemon() { return activePokemon; }
    public String getName() { return name; }
    public List<Pokemon> getTeam() { return team; }
}