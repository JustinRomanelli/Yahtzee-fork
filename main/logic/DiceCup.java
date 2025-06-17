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

    public boolean[] getHeld() {
        return this.hold;
    }

    public void setHandVal(int val, int index) {
        this.hand[index] = val;
    }

    public void setHeld(boolean[] held) {
        this.hold = held;
    }

    public void setHeld(int i, boolean value) {
        this.hold[i] = value;
    }

    public int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }

    public int[] rollAllDice() {
        for (int i = 0; i < 5; i++) {
            if (!this.hold[i]) {
                this.hand[i] = this.rollDie();
            }
        }
        
        return this.hand;
    }
}

