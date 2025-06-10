package main.logic;

import java.util.Scanner;

public class Player {
    private String name;
    private Scoresheet playerScoresheet;
    Scanner input = new Scanner(System.in);

    // More methods may be necessary for accessing and mutating the Scoresheet object for each player.

    public Player(String name, Scoresheet playerScoresheet) {
        // Commented out ONLY for testing.
        // System.out.println("Enter Player Name: ");
        // this.name = input.nextLine();
        this.playerScoresheet = playerScoresheet;

        input.close();
    }

    public String getName() {
        return name;
    }

    public Scoresheet getScoresheet() {
        return this.playerScoresheet;
    }

    // Returns the array of all scores.
    public int[] getScore() {
        return playerScoresheet.getBoard();
    }

    // Updates one score, based on the index.
    public void addToScore(int number, int index) {
        this.playerScoresheet.setScore(number, index);
    }
}
