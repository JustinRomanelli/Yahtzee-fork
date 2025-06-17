package main.logic;

import java.util.ArrayList;

import main.logic.DiceCup;
import main.logic.Player;

public class Scoresheet {
    private DiceCup cup;
    private int[] hand_OLD;
    private int[] board;
    public String[] categories = {"Ones", "Twos", "Threes", "Fours", "Fives", "Sixes",
                                   "Three of a kind", "Four of a kind", "Full house", "Small straight", "Large straight", "Yahtzee", "Chance"};
    private boolean[] unusedCategories;

    public Scoresheet(DiceCup cup) {
        this.cup = cup;
        this.hand_OLD = cup.getHand();
        this.board = makeBoard();
        this.unusedCategories = makeCategoryList();
    }

    public DiceCup getDiceCup() {
        return this.cup;
    }

    // Returns the current board.
    // Counts as the "scores"
    public int[] getBoard() {
        return this.board;
    }

    public int getTotalScore() {
        int total = 0;

        for (int i = 0; i < this.board.length; i++) {
            total += this.board[i];
        }

        return total;
    }

    // Sets a score of the board to the given value.
    public void setScore(int val, int index) {
        this.board[index] = val;
    }

    public void setCategoryAsUsed(int index) {
        this.unusedCategories[index] = false;
    }

    /* makeBoard() creates a board with 14 categories
     * @return: array of integers representing the board
     */
    public int[] makeBoard() {
        int[] board = new int[14];
        for (int i = 0; i < board.length; i++) {
            board[i] = 0; // 0 means unused
        }
        return board;
    }

    /* makeCategoryList() makes list of categories that haven't been used yet
    * @return: array list of categories
    */
    public boolean[] makeCategoryList() {
        boolean[] arr = new boolean[13];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = true;
        }

        return arr;
    }
    
    /* unused() checks to see if the category is used before
     * @param: categoryIndex
     * @return: true if unused, false if used
     */
    public boolean unused(int categoryIndex) {
        return this.unusedCategories[categoryIndex];
    }

    /* scoreHand() returns score of hand for given category
     * @param: categoryIndex
     * @return: score
     */
    public int scoreHand(int categoryIndex, int[] hand) {
        if (categoryIndex < 6) {
            return scoreSimple(categoryIndex, hand);
        }
        else {
            return scoreComplex(categoryIndex, hand);
        }
    }

    /* scoreSimple() scores categories with index less than 6
     * @param: categoryIndex
     * @return: score
     */

    public int scoreSimple(int categoryIndex, int[] hand) {
        int total = 0;
    
        for (int value : hand) {
            if (value == categoryIndex + 1) { // value is base 1, categoryIndex is base 0, so add 1.
                total += value;
            }
        }

        this.unusedCategories[categoryIndex] = false;

        this.setScore(total, categoryIndex);

        return total;
    }

    /* Scorecomplex() contains methods to get the score for categories with index greater than 5
     * NOTE: DOES NOT CHECK TO SEE IF A SCORE IS VALID
     * @param: categoryIndex
     * @return: score
     */

    public int scoreComplex(int categoryIndex, int[] hand) {
        
        int total = 0;

        switch (categoryIndex) {
            // 3 of a kind
            case 6:
                for (int val : hand) {
                    total += val;
                }

                this.unusedCategories[categoryIndex] = false;
                
                break;
            // 4 of a kind
            case 7:
                for (int val : hand) {
                    total += val;
                }

                this.unusedCategories[categoryIndex] = false;

                break;
            // Full House
            case 8:
                total = 25;
                this.unusedCategories[categoryIndex] = false;
                break;
            // Small Straight
            case 9:
                total = 30;
                this.unusedCategories[categoryIndex] = false;
                break;
            // Large Straight
            case 10:
                total = 40;
                this.unusedCategories[categoryIndex] = false;
                break;
            // Yahtzee
            case 11:
                total = 50;
                this.unusedCategories[categoryIndex] = false;
                break;
            // Chance
            case 12:
                for (int val : hand) {
                    total += val;
                }
                this.unusedCategories[categoryIndex] = false;
                break;
        }

        this.setScore(total, categoryIndex);

        return total;
    }

    // Convenient method to reset an int array.
    private int[] resetIntArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }

        return arr;
    }

    // Verifies that a hand can be scored. Checks ALL categories at once.
    // Returns a boolean[] that contains whether or not each category can be scored.
    public boolean verify(int categoryIndex, int[] hand) {

        int repeat = 0;

        int[] repeatArr = new int[6];

        // Ones through Sixes (indicies 0-5)

        if (categoryIndex >= 0 && categoryIndex <= 5) {
            for (int val : hand) {
                if (val == categoryIndex + 1 && this.unusedCategories[categoryIndex]) {
                    return true;
                }
            }
        }

        // 3 of a kind (index 6), 4 of a kind (index 7), Yahtzee (index 11)

        if (categoryIndex == 6 || categoryIndex == 7 || categoryIndex == 11) {
            for (int i = 1; i < 7; i++) {
                repeat = 0;
                for (int val : hand) {
                    if (val == i) {
                        repeat++;
                    }
                }
                
                if (categoryIndex == 6 && repeat >= 3 && this.unusedCategories[6]) {
                    return true;
                }
                if (categoryIndex == 7 && repeat >= 4 && this.unusedCategories[7]) {
                    return true;
                }
                if (categoryIndex == 11 && repeat == 5 && this.unusedCategories[11]) {
                    return true;
                }
            }
        }

        // Full House (index 8)
        // NOTE: May not work entirely correctly.
        
        repeatArr = resetIntArr(repeatArr);
        
        if (categoryIndex == 8) {
            for (int i = 1; i < 7; i++) {
                for (int j = 0; j < hand.length; j++) {
                    if (hand[j] == i) {
                        repeatArr[i - 1]++;
                    }
                }
            }

            // TODO: Not confident on this logic, check this later.
            boolean threeInARow = false;
            boolean twoInARow = false;

            for (int val : repeatArr) {
                if (val >= 3) {
                    threeInARow = true;
                }
                else if (val == 2) {
                    twoInARow = true;
                }
            }

            if (threeInARow && twoInARow && this.unusedCategories[8]) {
                return true;
            }
        }

        // Small Straight (index 9), Large Straight (index 10)

        if (categoryIndex == 9 || categoryIndex == 10) {
            int streakUp = 1; // Start at 1, since the first value counts toward the streak.
            int streakDown = 1;

            int lowest = 999; // Arbitrary large and small values, since they will be overridden in a moment anyways.
            int highest = -1;

            for (int i = 0; i < hand.length; i++) {
                if (hand[i] > highest) {
                    highest = hand[i];
                }
                if (hand[i] < lowest) {
                    lowest = hand[i];
                }
            }

            int current = lowest;
            int next = current + 1;

            // Lowest --> Counting up
            for (int i = 0; i < hand.length; i++) {
                if (hand[i] == next) {
                    current = next;
                    next = current + 1;
                    i = -1; // -1, since it will then increment back to 0.
                    streakUp++;
                }
            }

            current = highest;
            next = current - 1;

            // Highest --> Counting down
            for (int i = 0; i < hand.length; i++) {
                if (hand[i] == next) {
                    current = next;
                    next = current - 1;
                    i = -1; // -1, since it will then increment back to 0.
                    streakDown++;
                }
            }

            if (categoryIndex == 9 && (streakUp >= 4 || streakDown >= 4) && this.unusedCategories[9]) {
                return true;
            }
            if (categoryIndex == 10 && (streakUp == 5 || streakDown == 5) && this.unusedCategories[10]) {
                return true;
            }
        }

        // Chance (index 12): Always available, just sets to true (as long as it hasn't already been used).
        if (categoryIndex == 12 && this.unusedCategories[12]) {
            return true;
        }

        return false;
    }
}
