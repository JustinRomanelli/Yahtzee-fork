package main.functions;

import java.util.Scanner;
import DiceCup.java;
import Player.java;

public class Round {
    private Scanner input;
    private DiceCup cup;

    public Round() {
        this.input = new Scanner(System.in);
        this.cup = new DiceCup();
    }


    private void takeTurn(Player player) {
        int roll = 1;
        boolean shouldContinue = true;

        while (roll <= 3 && shouldContinue) {
            // Sets all 5 dice to be rolled on 1st round
            if (roll == 1) {
                int[] held = {False, False, False, False};
                cup.setHeld(held);
            }
            
            // Rolls the amount of dice not being held
            boolean[] diceToRoll = DiceCup.getHeld();
            int[] dice = DiceCup.rollDice(diceToRoll);
            player.addToScore(dice);

            // Asks user whether to roll again
            System.out.println("Would you like to roll again? (Y/N) ");
            String rollAgain = input.nextLine().toLowerCase();

            if (rollAgain.equals("n")) {
                shouldContinue = false;
                System.out.println("Scoring now.");
            }

            // Asks the user for input & updates the hold array
            else {
                for (int i = 0; i < cup.getHeld().length; i++) {
                    System.out.println("Hold die #" + (i + 1) + "? (Y/N) "); // Adding 1 to the number of dice; Die #1 rather than Die #0 for first die
                    String holdDie = input.nextLine().toLowerCase();
                    if (holdDie.equals("y")) {
                        cup.setHeld(i, true);
                    }
                }
            }

            // Increases the roll count for a maximum of 3 rolls
            roll++;
        }


    }

    /******** Instructions ********
    First roll: all 5 dice
    Set keepers aside
    score now? or roll again?

    Second roll: Reroll any/all dice you want
    no need to declare combination to roll for.

    Third (final roll): reroll any dice you want
    After, must fill in a box on score card with a score or zero */

}
