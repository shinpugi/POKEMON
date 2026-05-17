package com.pokemon.engine;

import com.pokemon.models.Trainer;
import com.pokemon.models.Pokemon;
import com.pokemon.models.Move;
import java.util.Scanner;
import java.util.Random;

public class BattleEngine {
    private Trainer player;
    private Trainer aiBot;
    private Scanner scanner;
    private Random random;

    public BattleEngine(Trainer player, Trainer aiBot) {
        this.player = player;
        this.aiBot = aiBot;
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    public void start() {

        System.out.println("\n\n\n\n\n\n\n*** BATTLE START: " + player.getName() + " vs " + aiBot.getName() + " ***");

        //Checks if active pokemon is fainted, then forces a switch if true
        while (player.hasAvailablePokemon() && aiBot.hasAvailablePokemon()) {
            if (player.getActivePokemon().isFainted()) {
                System.out.println("\nYour " + player.getActivePokemon().getName() + " fainted!");
                forcePlayerSwitch();
            }
            if (aiBot.getActivePokemon().isFainted()) {
                System.out.println("\nOpponent's " + aiBot.getActivePokemon().getName() + " fainted!");
                forceAiSwitch();
            }
            if (player.hasAvailablePokemon() && aiBot.hasAvailablePokemon()) {
                executeTurn();
            }
        }

        //Checks if there are alive pokemons left
        if (player.hasAvailablePokemon()) {
            System.out.println("\n*** YOU WON THE BATTLE! ***");
        } else {
            System.out.println("\n*** You blacked out... ***");
        }
    }

    //Starts the turn
    private void executeTurn() {
        Pokemon pActive = player.getActivePokemon();
        System.out.println("\n==================================");
        System.out.println(player.getName() + "'s Lv.67 " + pActive.getName() + " [HP: " + pActive.getHpBar() + "]");
        //System.out.println(aiBot.getName() + "'s Lv. 67 " + aiBot.getActivePokemon().getName() + " [HP: " + aiBot.getActivePokemon().getHpBar() + "]");
        System.out.println(aiBot.getName() + "'s Lv. 67 " + aiBot.getActivePokemon().getName() + " [HP: " + aiBot.getActivePokemon().getHpBar() + "] " + aiBot.getItemCount("Full Restore Potion") + " Potions");
        System.out.println("----------------------------------");
        System.out.println("1. Attack  2. Full Restore (" + player.getItemCount("Full Restore Potion") + " left)  3. Switch");
        System.out.print("Choice: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    if (!handleAttackUI(pActive)) return;
                    break;
                case 2:
                    player.useItem("Full Restore Potion");
                    break;
                case 3:
                    if (!voluntaryPlayerSwitch()) return;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    return;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // Enemy AI
        if (!aiBot.getActivePokemon().isFainted() && !player.getActivePokemon().isFainted()) {
            System.out.println("\n--- Opponent's Turn ---");
            Pokemon aiActive = aiBot.getActivePokemon();

            int hpPercent = (aiActive.getHp() * 100) / aiActive.getMaxHp();

            try {
                if (hpPercent < 30 && aiBot.getItemCount("Full Restore Potion") > 0 && random.nextInt(100) < 50) {
                    // Enemy can randomly heal under 30% HP
                    aiBot.useItem("Full Restore Potion");
                } else if (random.nextInt(100) < 15 && hasOtherAwakePokemon(aiBot)) {
                    // Enemy can switch with 15% chance
                    System.out.println("Opponent decided to switch Pokemon!");
                    forceAiSwitch();
                } else {
                    java.util.List<Integer> availableMoves = new java.util.ArrayList<>();
                    for (int i = 0; i < aiActive.getMoves().size(); i++) {
                        if (aiActive.getMoves().get(i).getCurrentPP() > 0) {
                            availableMoves.add(i);
                        }
                    }

                    // Randomly pick from the valid moves
                    if (!availableMoves.isEmpty()) {
                        int randomMoveIndex = availableMoves.get(random.nextInt(availableMoves.size()));
                        aiBot.getActivePokemon().useMove(randomMoveIndex, player.getActivePokemon());
                    } else {
                        // Use Struggle if out of PP
                        System.out.println(aiBot.getName() + "'s " + aiActive.getName() + " has no PP left for any moves and is struggling!");
                        player.getActivePokemon().takeDamage(15);
                        aiActive.takeDamage(15); // Struggle causes recoil to the user
                    }
                }
            } catch (Exception e) {
                int randomMove = random.nextInt(aiActive.getMoves().size());
                aiBot.getActivePokemon().useMove(randomMove, player.getActivePokemon());
            }
        }
    }

    //Displays available moves
    private boolean handleAttackUI(Pokemon active) {
        while (true) {
            System.out.println("\nChoose a move:");
            for (int i = 0; i < active.getMoves().size(); i++) {
                Move m = active.getMoves().get(i);
                System.out.println((i + 1) + ". " + m.getName() + " (Power: " + m.getBasePower() + ", Type: " + m.getType() + ") [PP: " + m.getCurrentPP() + "/" + m.getMaxPp() + "]");
            }
            System.out.println("0. Back");
            System.out.print("Choice: ");

            try {
                int input = Integer.parseInt(scanner.nextLine());
                System.out.println("\n\n\n");
                if (input == 0) return false;

                int moveChoice = input - 1;
                if (moveChoice >= 0 && moveChoice < active.getMoves().size()) {

                    if (active.getMoves().get(moveChoice).getCurrentPP() <= 0) {
                        System.out.println("There's no PP left for this move! Choose another one.");
                        continue;
                    }

                    active.useMove(moveChoice, aiBot.getActivePokemon());
                    return true;
                } else {
                    System.out.println("Invalid move selection. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid move selection. Try again.");
            }
        }
    }

    //Shows switch option when active pokemon fainted
    private void forcePlayerSwitch() {
        while (true) {
            System.out.println("\nChoose a Pokemon to send out:");
            for (int i = 0; i < player.getTeam().size(); i++) {
                Pokemon p = player.getTeam().get(i);
                String status = p.isFainted() ? "[FAINTED]" : "[HP: " + p.getHp() + "]";
                System.out.println((i + 1) + ". " + p.getName() + " " + status);
            }
            try {
                System.out.print("Choice: ");
                int choice = Integer.parseInt(scanner.nextLine()) - 1;
                player.setActivePokemon(choice);
                System.out.println("Go, " + player.getActivePokemon().getName() + "!");
                break;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void forceAiSwitch() {
        for (int i = 0; i < aiBot.getTeam().size(); i++) {
            if (!aiBot.getTeam().get(i).isFainted() && aiBot.getTeam().get(i) != aiBot.getActivePokemon()) {
                try {
                    aiBot.setActivePokemon(i);
                    System.out.println("\nOpponent sent out " + aiBot.getActivePokemon().getName() + "!");
                    return;
                } catch (Exception e) { }
            }
        }
    }

    //Checks if any pokemon is alive
    private boolean hasOtherAwakePokemon(Trainer trainer) {
        for (Pokemon p : trainer.getTeam()) {
            if (p != trainer.getActivePokemon() && !p.isFainted()) return true;
        }
        return false;
    }

    //Shows switch option when player chooses switch option
    private boolean voluntaryPlayerSwitch() {
        while (true) {
            System.out.println("\nChoose a Pokemon to send out:");
            for (int i = 0; i < player.getTeam().size(); i++) {
                Pokemon p = player.getTeam().get(i);
                String status = p.isFainted() ? "[FAINTED]" : "[HP: " + p.getHp() + "]";
                String activeMarker = (p == player.getActivePokemon()) ? " (Active)" : "";
                System.out.println((i + 1) + ". " + p.getName() + " " + status + activeMarker);
            }
            System.out.println("0. Back");

            try {
                System.out.print("Choice: ");
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 0) return false; // DONT SWITCH

                int choice = input - 1;
                player.setActivePokemon(choice);
                System.out.println("Go, " + player.getActivePokemon().getName() + "!");
                return true; // SWITCH
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

}