package main.ui.event;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.ui.GameGraphics;
import main.ui.ScoringMenuUI;

public class RollButtonAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < GameGraphics.allDice.length; i++) {
            if (!GameGraphics.allDice[i].getIsHeld()) {
                int val = GameGraphics.p1.getScoresheet().getDiceCup().rollDie();
                // System.out.println("Die #" + i + " Rollled: Result = " + val);
                GameGraphics.p1.getScoresheet().getDiceCup().setHandVal(val, i);

                GameGraphics.redrawDie("./imgs/die_" + val + ".png", 175 + (225 * i), 150, i);
            }
        }

        GameGraphics.enabledScoring = GameGraphics.p1.getScoresheet().verify();

        ScoringMenuUI.enableScoringButtons(GameGraphics.enabledScoring);

        GameGraphics.incrementRolls();
    }
}
