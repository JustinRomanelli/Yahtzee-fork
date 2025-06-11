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

  private int[] scores;

  public ScoreButtonAction(int index) {
    this.btnIndex = index;

    this.scores = new int[13];

    for (int i = 0; i < this.scores.length; i++) {
      scores[i] = 0;
    }
  }

  public void actionPerformed(ActionEvent e) {
    // Increment the round number.
    GameGraphics.incrementRound();

    // Add to the total score display
    int newScore = GameGraphics.p1.getScoresheet().scoreHand(btnIndex);

    System.out.println("New Score: " + newScore);

    this.scores[btnIndex] = newScore;

    ScoringMenuUI.totalIntScore += newScore;

    // Reset variables
    ScoringMenuUI.buttonIDs[btnIndex] = String.valueOf(this.scores[btnIndex]);

    GameGraphics.p1.getScoresheet().setCategoryAsUsed(btnIndex);
 
    // Rebuild the scoring menu.
    // Reroll all of the dice.
    ScoringMenuUI.container.removeAll();

    for (int i = 0; i < GameGraphics.allDice.length; i++) {
      int val = GameGraphics.p1.getScoresheet().getDiceCup().rollDie();
      
      GameGraphics.p1.getScoresheet().getDiceCup().setHandVal(val, i);

      GameGraphics.redrawDie("./imgs/die_" + val + ".png", 175 + (225 * i), 150, i);
    }

    // Reset the scoring buttons' "enabled" status
    GameGraphics.enabledScoring = GameGraphics.p1.getScoresheet().verify();

    // Reset the hold buttons back to default
    GameGraphics.resetAllHoldButtons();

    // Rebuild the scoring container
    // Then, enable only the scoring buttons that can be properly scored
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

      if (!ScoringMenuUI.buttonIDs[i].equals("Score")) {
        label = new JLabel(String.valueOf(GameGraphics.p1.getScoresheet().getBoard()[i]));

        label.setBorder(border);

        label.setHorizontalAlignment(JLabel.CENTER);

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

    ScoringMenuUI.totalScore = new JLabel(String.valueOf(GameGraphics.p1.getScoresheet().getTotalScore()));

    scoreLabel.setBorder(border);
    ScoringMenuUI.totalScore.setBorder(border);

    scoreLabel.setHorizontalAlignment(JLabel.CENTER);
    ScoringMenuUI.totalScore.setHorizontalAlignment(JLabel.CENTER);

    container.add(scoreLabel);
    container.add(ScoringMenuUI.totalScore);
  }
}
