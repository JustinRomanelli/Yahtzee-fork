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

    public String getInput() {
        Scanner s = new Scanner(System.in);

        return s.nextLine();
    }

    public int getIntInput() {
        Scanner s = new Scanner(System.in);

        return s.nextInt();
    }

    // Takes the player's turn.
    // A return of true indicates that the game should continue.
    // A return of false indicates that the game should be finished.
    public boolean takeTurn() {
        int roll = 1;
        boolean shouldContinue = true;
        boolean isGameFinished = false;

        while (roll <= 3 && shouldContinue) {

            isGameFinished = false;

            // Sets all 5 dice to be rolled on 1st round
            if (roll == 1) {
                boolean[] held = {false, false, false, false, false};
                cup.setHeld(held);
            }
            
            // Rolls the amount of dice not being held
            int[] dice = cup.rollAllDice();
            
            // Print all of the dice

            for (int i = 0; i < dice.length; i++) {
                if (cup.getHeld()[i]) {
                    System.out.println(dice[i] + ": Held");
                }
                else {
                    System.out.println(dice[i] + ": NOT Held");
                }
            }

            System.out.println();

            String rollAgain;

            if (roll < 3) {
                // Asks user whether to roll again
                System.out.println("Would you like to roll again? (Y/n) ");
                rollAgain = this.getInput();
            }
            else {
                rollAgain = "n";
            }

            if (rollAgain.equals("n")) {
                shouldContinue = false;

                // Get a list of all of the categories, and which ones can be stored.
                boolean[] canScoreList = new boolean[13];

                for (int i = 0; i < canScoreList.length; i++) {
                    canScoreList[i] = scoresheet.verify(i, cup.getHand());
                }

                // Print all of the categories.
                // Also checks that the game should continue running
                
                boolean openCategories = false;
                
                for (int i = 0; i < canScoreList.length; i++) {
                    if (canScoreList[i]) {
                        if (scoresheet.unused(i)) { // If a category hasn't been scored yet
                            System.out.print(scoresheet.categories[i] + " (" + (i + 1) + "): Yes     ");
                            openCategories = true;
                        }
                        else {
                            System.out.print(scoresheet.categories[i] + " (" + (i + 1) + "): Scored     ");
                        }
                    }
                    else {
                        if (scoresheet.unused(i)) {
                            System.out.print(scoresheet.categories[i] + " (" + (i + 1) + "): No     ");
                        }
                        else {
                            System.out.print(scoresheet.categories[i] + " (" + (i + 1) + "): Scored     ");
                        }
                    }
                    if (i % 3 == 2) {
                        System.out.println();
                    }
                }

                // If there are no categories opened (false), the game is finished.
                // Otherwise, it should continue going.
                isGameFinished = !openCategories;

                // Choose a category to score
                System.out.println("\nSelect a category to score: (1-13)");
                int scoringIndex = this.getIntInput();

                // Score the result and print the total score.
                player.addToScore(scoresheet.scoreHand(scoringIndex - 1, dice), scoringIndex - 1);

                int totalScore = scoresheet.getTotalScore();

                System.out.println("Your total score is now: " + totalScore + ".");
            }

            // Asks the user for input & updates the hold array
            else {
                for (int i = 0; i < cup.getHeld().length; i++) {
                    System.out.println("Hold die #" + (i + 1) + "? (y/N) "); // Adding 1 to the number of dice; Die #1 rather than Die #0 for first die
                    String holdDie = getInput().toLowerCase();
                    if (holdDie.equals("y")) {
                        cup.setHeld(i, true);
                    }
                    else {
                        cup.setHeld(i, false);
                    }
                }
            }

            // Increases the roll count for a maximum of 3 rolls
            roll++;
        }

        return !isGameFinished;
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
