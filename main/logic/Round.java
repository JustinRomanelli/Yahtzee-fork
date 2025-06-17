package main.logic;

import java.util.Scanner;
import main.logic.DiceCup;
import main.logic.Player;

public class Round {
    private Scanner input;
    private Player player;
    private Scoresheet scoresheet;
    private DiceCup cup;

    public Round(Scanner s, Player p) {
        this.input = s;
        this.player = p;
        this.scoresheet = p.getScoresheet();
        this.cup = p.getScoresheet().getDiceCup();
    }


    public void takeTurn() {
        int roll = 1;
        boolean shouldContinue = true;

        while (roll <= 3 && shouldContinue) {
            // Sets all 5 dice to be rolled on 1st round
            if (roll == 1) {
                boolean[] held = {false, false, false, false, false};
                cup.setHeld(held);
            }
            
            // Rolls the amount of dice not being held
            int[] dice = cup.rollAllDice();
            
            // Print all of the dice

            for (int num : dice) {
                System.out.print(num + " ");
            }

            System.out.println();

            // Asks user whether to roll again
            System.out.println("Would you like to roll again? (Y/N) ");
            // String rollAgain = this.input.nextLine();
            this.input.next();

            String rollAgain = "n";

            if (rollAgain.equals("n")) {
                shouldContinue = false;

                boolean[] canScoreList = new boolean[13];

                for (int i = 0; i < canScoreList.length; i++) {
                    canScoreList[i] = scoresheet.verify(i, cup.getHand());
                }

                for (int i = 0; i < canScoreList.length; i++) {
                    if (canScoreList[i]) {
                        System.out.print(scoresheet.categories[i] + " (" + (i + 1) + "): Yes     ");
                    }
                    else {
                        System.out.print(scoresheet.categories[i] + " (" + (i + 1) + "): No     ");
                    }
                    if (i % 3 == 2) {
                        System.out.println();
                    }
                }

                System.out.println("\nSelect a category to score: ");
                // String result = this.getInput();



                // TODO: Implement verify() here to check for which categories can be scored.
                // Then, ask which one to score.
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

    // private String getInput() {
    //     Scanner scanner = new Scanner(System.in);

    //     System.out.println(scanner.hasNextLine());

    //     String result = scanner.nextLine();

    //     return result;
    // }

    /******** Instructions ********
    First roll: all 5 dice
    Set keepers aside
    score now? or roll again?

    Second roll: Reroll any/all dice you want
    no need to declare combination to roll for.

    Third (final roll): reroll any dice you want
    After, must fill in a box on score card with a score or zero */

}
