package main.logic;

import java.lang.Math;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/*
 * An implementation of the ImageIcon class, which allows us to specify the die's number.
 */
public class Die extends ImageIcon {
    
    /*
     * PATH is the file path to the image.
     * num is the current value of the die (1-6), inclusive
     * isHeld represents whether the die is being held or not.
     */
    private String PATH;
    private int num;
    private int index;
    private boolean isHeld;

    public Die(String path, int index) {
        super(path);
        this.PATH = path;
        this.num = 1;
        this.index = index;
        this.isHeld = false;
    }

    public int getNum() {
        return this.num;
    }

    public String getPath() {
        return this.PATH;
    }

    public boolean getIsHeld() {
        return this.isHeld;
    }

    public int getIndex() {
        return this.index;
    }

    public static int rollDie() {
        return (int) (Math.random() * 6) + 1;
    }

    /*
     * Both sets the num variable AND updates the PATH variable.
     */
    public void setDieNum(int newNum) {
        this.num = newNum;
        this.PATH = "./imgs/die_" + newNum + ".png";
    }

    public void setPath(String path) {
        this.PATH = path;
    }

    public void toggleHeld() {
        this.isHeld = !this.isHeld;
    }
}
