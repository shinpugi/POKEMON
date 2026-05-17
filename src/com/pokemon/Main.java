package com.pokemon;

import com.pokemon.engine.BattleEngine;
import com.pokemon.engine.RosterSeeder;
import com.pokemon.models.Pokemon;
import com.pokemon.models.Trainer;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter your Player name: ");
        String playerName = scanner.nextLine();
        System.out.print("Enter your Rival's name: ");
        String rivalName = scanner.nextLine();
        Trainer player = new Trainer(playerName);
        Trainer aiBot = new Trainer(rivalName);

        List<Pokemon> pool = RosterSeeder.generatePool();

        //TITLE SCREEN
        System.out.print("""
                    =========================================
                      ___  ___  _  __ ___  __  __  ___  _  _\s
                     | _ \\/ _ \\| |/ /| __||  \\/  |/ _ \\| \\| |
                     |  _/ (_) | ' < | _| | |\\/| | (_) | .` |
                     |_|  \\___/|_|\\_\\|___||_|  |_|\\___/|_|\\_|
                    =========================================
                """);
        System.out.println("=== WELCOME TO PANG PANGMALAKASANG POKEMON GAME ===\n\n");

        System.out.println("\t\t\t== POKEMON SELECTION PHASE ==");
        int halfSize = pool.size() / 2;

        for (int i = 0; i < halfSize; i++) {
            Pokemon leftP = pool.get(i);
            Pokemon rightP = pool.get(i + halfSize);
            String leftString = String.format("%2d. %s [%s]", (i + 1), leftP.getName(), leftP.getType());
            String rightString = String.format("%2d. %s [%s]", (i + 1 + halfSize), rightP.getName(), rightP.getType());
            System.out.printf("%-35s %s\n", leftString, rightString);
        }

        System.out.println("\nSelect your team of 5! (Enter numbers 1-20):");
        while (player.getTeam().size() < 5) {
            System.out.print("Select Pokemon #" + (player.getTeam().size() + 1) + ": ");
            try {
                int choice = Integer.parseInt(scanner.nextLine()) - 1;
                if (choice >= 0 && choice < pool.size()) {
                    String chosenName = pool.get(choice).getName();

                    // Check for duplicates
                    if (player.hasPokemon(chosenName)) {
                        System.out.println("You already have " + chosenName + " on your team! Choose a different one.");
                    } else {
                        player.addPokemon(RosterSeeder.generatePool().get(choice));
                        System.out.println("Added " + chosenName + " to your team!");
                    }
                } else {
                    System.out.println("Invalid number. Please enter 1-20.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }

        //Enemy chooses 5 random pokemon
        System.out.println("\nOpponent is randomly drafting their team...");
        while (aiBot.getTeam().size() < 5) {
            int randomChoice = random.nextInt(pool.size());
            String chosenName = pool.get(randomChoice).getName();
            if (!aiBot.hasPokemon(chosenName)) {
                aiBot.addPokemon(RosterSeeder.generatePool().get(randomChoice));
            }
        }

        //Displays enemy pokemons
        System.out.println("\n=== " + aiBot.getName() + "'S PARTY ===");
        System.out.println("Rival " + aiBot.getName() + " has drafted their team!");
        for (int i = 0; i < aiBot.getTeam().size(); i++) {
            Pokemon p = aiBot.getTeam().get(i);
            System.out.printf("- %s [%s]\n", p.getName(), p.getType());
        }
        System.out.println("=======================");

        System.out.println("\nPress ENTER to start the battle...");
        scanner.nextLine();

        try {
            player.setActivePokemon(0);
            aiBot.setActivePokemon(0);
        } catch (Exception e) {
            System.out.println("Error setting initial Pokemon.");
        }

        //Starts the game
        BattleEngine engine = new BattleEngine(player, aiBot);
        engine.start();

        scanner.close();
    }
}