package main.logic;

import java.lang.Math;
import main.logic.Player;
import main.logic.Die;
import main.logic.DiceCup;
import main.logic.Scoresheet;
import main.ui.GameGraphics;

/*
 * Contains all of the logic to run the game, in the form of helper methods
 * Examples: a method to roll the dice, a method that would toggle the "Hold" functionality, etc.
 */
public class GameLogic {

    /*
     * Creates the two players.
     * Takes in parameters for the players' names
     * Returns: An array of type Player with two Players initialized.
     */
    public static Player[] initPlayers(String name1, String name2) {
        Player[] players = new Player[2];
        players[0] = new Player(name1, new Scoresheet(new DiceCup()));
        players[1] = new Player(name2, new Scoresheet(new DiceCup()));
        return players;
    }

    /*
     * Currently a dummy method for testing event listeners.
     * Will later be used for setting a die to be held.
     */
    public static void holdDie(Die die) {
        System.out.println("Holding die...");
        
        die.toggleHeld();

        // die.getIndex() is always the same as btn.getIndex().
        GameGraphics.colorHoldButton(die.getIsHeld(), die.getIndex());
    }

    // TODO: Implement algorithm to select first user based on highest first roll

    /*
     * Takes in two numbers (die roll for players 1 and 2).
     * Returns the order of the players. 
     * ! May later refactor this to take in an array of dice rolls (one for each player)
     */
    public static int[] choosePlayOrder(int roll1, int roll2) {
        int[] order = new int[2];
        
        if (roll1 < roll2) {
            order[0] = 2;
            order[1] = 1;
            return order;
        }
        else {
            order[0] = 1;
            order[1] = 2;
            return order;
        }
    }
}
