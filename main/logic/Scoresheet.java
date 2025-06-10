package main.logic;

import java.util.ArrayList;

import main.logic.DiceCup;
import main.logic.Player;

public class Scoresheet {
    private DiceCup cup;
    private int[] hand;
    private int[] board;
    private String[] categories = {"ones","twos","threes","fours","fives","sixes","upper section bonus","three of a kind","four of a kind","full house","small straight","large straight","chance","yahtzee"};
    private ArrayList<String> unusedCategories;

    public Scoresheet(DiceCup cup) {
        this.cup = cup;
        this.hand = cup.getHand();
        this.board = makeBoard();
        this.unusedCategories = makeCategoryList();
    }

    // Returns the current board.
    // Counts as the "scores"
    public int[] getBoard() {
        return this.board;
    }

    // Sets a score of the board to the given value.
    public void setScore(int val, int index) {
        this.board[index] = val;
    }

    /* validCategory() checks to see if the category is valid
     * @param: category
     * @return: true if valid, false if invalid
     */
    public boolean validCategory(String category) {
        for (int i = 0; i < this.categories.length; i++) {
            if (categories[i].compareTo(category) == 0) {
                return unused(category);
            }
        }
        return false;
    }

    /* makeBoard() creates a board with 14 categories
     * @return: array of integers representing the board
     */
    public int[] makeBoard() {
        int[] board = new int[14];
        for (int i = 0; i < board.length; i++) {
            board[i] = -1; // -1 means unused
        }
        return board;
    }

    /* makeCategoryList() makes list of categories that haven't been used yet
    * @return: array list of categories
    */
    public ArrayList<String> makeCategoryList() {
        ArrayList<String> unused = new ArrayList<String>();
        return unused;
    }
    
    /* unused() checks to see if the category is used before
     * @param: category
     * @return: true if unused, false if used
     */
    public boolean unused(String category) {
        //  for (int i = 0; i < this.unusedCategories.size(); i++) {
        //     if (unusedCategories.get(i).contains(category)) {
                

        //  }
        return true;
    }

    /* scoreHand() returns score of hand for given category
     * @param: category
     * @return: score
     */
    public int scoreHand(String category) {
        // There's a better way to do this, but this'll work for now.
        
        boolean hasCategory = false;
        int categoryIndex = -1;

        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(category)) {
                hasCategory = true;
                categoryIndex = i;
            }
        }

        if (hasCategory) {
            if (categoryIndex < 6) {
                return scoreSimple(categoryIndex);
            }
            else {
                return scoreComplex(categoryIndex);
            }
        }

        // 0 denotes a failure here.
        return 0;
    }

    /* scoreSimple() scores categories with index less than 6
     * @param: categoryIndex
     * @return: score
     */

    public int scoreSimple(int categoryIndex) {
        int total = 0;
    
        for (int value : hand) {
            if (value == categoryIndex + 1) { // value is base 1, categoryIndex is base 0, so add 1.
                total += value;
            }
        }

        return total;
    }

    /* Scorecomplex() contains methods to get the score for categories with index greater than 5
     * NOTE: DOES NOT CHECK TO SEE IF A SCORE IS VALID
     * @param: categoryIndex
     * @return: score
     */

    public int scoreComplex(int categoryIndex) {
        
        int total = 0;

        switch (categoryIndex) {
            // 3 of a kind
            case 6:
                for (int val : hand) {
                    total += val;
                }
                break;
            // 4 of a kind
            case 7:
                for (int val : hand) {
                    total += val;
                }
                break;
            // Full House
            case 8:
                total = 25;
                break;
            // Small Straight
            case 9:
                total = 30;
                break;
            // Large Straight
            case 10:
                total = 40;
                break;
            // Yahtzee
            case 11:
                total = 50;
                break;
            // Chance
            case 12:
                for (int val : hand) {
                    total += val;
                }
                break;
        }

        return total;
    }

    // Verifies that a hand can be scored under a specific category.
    public boolean verify(int categoryIndex) {
        if (categoryIndex == 6) {
            for(int i = 0; i < 6; i++) {
                int repeat = 0;
                // NOTE: < or <=?
                for(int j = 0; j < hand.length; j++) {
                    if(hand[j] == i + 1) {
                        repeat++;
                    }
                }
                if (repeat >= 3) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (categoryIndex == 7) {
             for(int i = 0; i < 6; i++) {
                int repeat = 0;
                for(int j = 0; j < hand.length; j++) {
                    if(hand[j] == i + 1) {
                        repeat++;
                    }
                }
                if (repeat >= 4) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        if (categoryIndex == 8) {
            for(int i = 0; i < 6; i++) {
                int repeat = 0;
                for(int j = 0; j < hand.length; j++) {
                    if(hand[j] == i + 1) {
                        repeat++;
                        
                    }
                }
                if (repeat >= 3) {
                    

                }

            }
        }
    }
}
