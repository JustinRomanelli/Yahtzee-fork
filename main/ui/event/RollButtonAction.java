package main.ui.event;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.ui.GameGraphics;

public class RollButtonAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // For now, all this does is update the roll number.
        // In the future, it will also have to actually roll the dice.

        GameGraphics.incrementRolls();
    }
}
