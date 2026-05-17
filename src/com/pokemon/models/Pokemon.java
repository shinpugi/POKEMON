package com.pokemon.models;

import com.pokemon.enums.PokemonType;
import com.pokemon.interfaces.Damageable;
import com.pokemon.interfaces.Healable;
import java.util.List;

//Uses implement to make sure pokemon can deal and heal damages
public abstract class Pokemon implements Damageable, Healable {
    private String name;
    private int level;
    private int hp;
    private int maxHp;
    private PokemonType type;
    private List<Move> moves;

    //Default constructor
    public Pokemon(String name, PokemonType type, List<Move> moves) {
        this.name = name;
        this.type = type;
        this.level = 67;
        this.maxHp = 200;
        this.hp = this.maxHp;
        this.moves = moves.size() > 4 ? moves.subList(0, 4) : moves;
    }

    //Checks if move is valid
    public void useMove(int moveIndex, Pokemon target) {
        if (moveIndex < 0 || moveIndex >= moves.size()) {
            System.out.println(this.name + " flailed around because that move doesn't exist!");
            return;
        }

        Move selectedMove = moves.get(moveIndex);

        //Checks if move still has PP
        if (!selectedMove.usePp()) {
            System.out.println(this.name + " tried to use " + selectedMove.getName() + " but has no PP left!");
            return;
        }

        System.out.println(">> " + this.name + " used " + selectedMove.getName() + "!");

        //Computes the final damage based on the multiplier in typings
        double multiplier = selectedMove.getType().getEffectiveness(target.getType());
        int finalDamage = (int) (selectedMove.getBasePower() * multiplier);

        if (multiplier > 1.0) {
            System.out.println("It's super effective!");
        } else if (multiplier < 1.0 && multiplier > 0.0) {
            System.out.println("It's not very effective...");
        } else if (multiplier == 0.0) {
            System.out.println("It had no effect!");
        }

        if ("MULTI_HIT".equals(selectedMove.getEffect())) {
            int hits = new java.util.Random().nextInt(4) + 2; // Hits 2 to 5 times
            System.out.println("It hit " + hits + " times!");
            finalDamage *= hits;
        }

        System.out.println("It dealt " + finalDamage + " damage!\n\n");
        target.takeDamage(finalDamage);

        if ("DRAIN".equals(selectedMove.getEffect()) && finalDamage > 0) {
            int healAmount = Math.max(1, finalDamage / 2); // Heals for 50% of damage dealt
            this.heal(healAmount);
            System.out.println(this.name + " drained " + healAmount + " HP from the target!");
        } else if ("RECOIL".equals(selectedMove.getEffect()) && finalDamage > 0) {
            int recoilDamage = Math.max(1, finalDamage / 4); // Takes 25% recoil damage
            this.takeDamage(recoilDamage);
            System.out.println(this.name + " is hit with recoil! Took " + recoilDamage + " damage!");
        }
    }

    //HP Bar
    public String getHpBar() {
        int totalBars = 10;
        int currentBars = (int) Math.round(((double) this.hp / this.maxHp) * totalBars);

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < totalBars; i++) {
            if (i < currentBars) bar.append("█"); // Solid block for current HP
            else bar.append("-");                 // Dash for missing HP
        }
        bar.append("] ").append(this.hp).append("/").append(this.maxHp);
        return bar.toString();
    }

    public static void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public Pokemon setMaxHealth(int maxHp) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        return this;
    }

    @Override
    public void takeDamage(int amount) {
        this.hp -= amount;
        if (this.hp < 0) this.hp = 0;
    }

    @Override
    public boolean isFainted() { return this.hp == 0; }

    @Override
    public void fullyRestore() {
        this.hp = this.maxHp;
        for (Move m : this.moves) {
            m.restorePp();
        }
    }

    @Override
    public void heal(int amount) { this.hp = Math.min(this.hp + amount, this.maxHp); }

    public String getName() { return name; }
    public PokemonType getType() { return type; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public List<Move> getMoves() { return moves; }
}