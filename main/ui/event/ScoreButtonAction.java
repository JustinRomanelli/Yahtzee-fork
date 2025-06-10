package main.ui.event;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

import main.ui.GameGraphics;
import main.ui.ScoringMenuUI;

public class ScoreButtonAction implements ActionListener {

  private int btnIndex;

  public ScoreButtonAction(int index) {
    this.btnIndex = index;
  }

  public void actionPerformed(ActionEvent e) {
    GameGraphics.incrementRound();

    ScoringMenuUI.buttonIDs[btnIndex] = "";

    GameGraphics.p1.getScoresheet().setCategoryAsUsed(btnIndex);
  
    ScoringMenuUI.container.removeAll();

    for (int i = 0; i < GameGraphics.allDice.length; i++) {
      int val = GameGraphics.p1.getScoresheet().getDiceCup().rollDie();
      
      GameGraphics.p1.getScoresheet().getDiceCup().setHandVal(val, i);

      GameGraphics.redrawDie("./imgs/die_" + val + ".png", 175 + (225 * i), 150, i);
    }

    GameGraphics.enabledScoring = GameGraphics.p1.getScoresheet().verify();

    rebuildContainer(ScoringMenuUI.container, ScoringMenuUI.labelIDs, ScoringMenuUI.buttonIDs);

    ScoringMenuUI.enableScoringButtons(GameGraphics.enabledScoring);

    ScoringMenuUI.container.revalidate();

    ScoringMenuUI.container.repaint();
  }

  private void rebuildContainer(JPanel container, String[] labels, String[] buttons) {
    Border border = BorderFactory.createLineBorder(Color.BLACK);

    for (int i = 0; i < buttons.length; i++) {
      JLabel label = new JLabel(labels[i]);

      label.setBorder(border);

      label.setHorizontalAlignment(JLabel.CENTER);

      container.add(label);

      if (ScoringMenuUI.buttonIDs[i].equals("")) {
        label = new JLabel("0");

        label.setBorder(border);

        label.setHorizontalAlignment(JLabel.CENTER);

        // TODO: MAY HAVE TO CHANGE THIS LINE.
        ScoringMenuUI.btnsScore[i] = null;

        container.add(label);
      }
      else {
        JButton button = new JButton(buttons[i]);

        button.setBorder(border);

        button.setHorizontalAlignment(JLabel.CENTER);

        button.addActionListener(new ScoreButtonAction(i));

        ScoringMenuUI.btnsScore[i] = button;

        // if (!GameGraphics.enabledScoring[i]) {
        //   button.setEnabled(false);
        // }

        container.add(button);
      }
    }

    JLabel scoreLabel = new JLabel("Total");

    // Placeholder 0
    ScoringMenuUI.totalScore = new JLabel("0");

    scoreLabel.setBorder(border);
    ScoringMenuUI.totalScore.setBorder(border);

    scoreLabel.setHorizontalAlignment(JLabel.CENTER);
    ScoringMenuUI.totalScore.setHorizontalAlignment(JLabel.CENTER);

    container.add(scoreLabel);
    container.add(ScoringMenuUI.totalScore);
  }
}
