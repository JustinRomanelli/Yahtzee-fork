package main.ui.event;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.logic.GameLogic;
import main.ui.GameGraphics;

public class HoldButtonAction implements ActionListener {
    
    private int btnIndex;

    public HoldButtonAction(int index) {
        this.btnIndex = index;
    }

    public void actionPerformed(ActionEvent e) {
        GameLogic.holdDie(GameGraphics.allDice[this.btnIndex]);
    }
}
