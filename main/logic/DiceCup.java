package main.logic;

public class DiceCup {

    private int[] hand;
    private boolean[] hold;

    public DiceCup() {
        hand = new int[5]; // {0, 0, 0, 0, 0};
        hold = new boolean[5]; // {false, false, false, false, false};
    
        for (int i = 0; i < 5; i++) {
            hand[i] = 0;
            hold[i] = false;
        }
    }

    public int[] getHand() {
        return this.hand;
    }

    public void setHandVal(int val, int index) {
        this.hand[index] = val;
        System.out.println("Setting " + val + " to index " + index);
    }

    public int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }

    // public int[] rerollDice() {
    //     for (int i = 0; i < hold.length; i++) {
    //         if (!hold[i]) {
    //             hand[i] = rollDie();
    //         }
    //
    //         int index = 0;
    //
    //         for (int j = 0; j < hand.length; j++) {
    //
    //             if (hand[j] != 0) {
    //                 index++;
    //             }
    //         }
    //
    //         int[] newArray = new int[index];
    //
    //         for (int k = 0; k < hand.length; k++) {
    //             if (hand[k] != 0) {
    //                 newArray[k] = hand[k];
    //             }
    //             else {
    //                 if (k > 0) {
    //                     k--;
    //                 }
    //             }
    //         }
    //     }
    // }
}
