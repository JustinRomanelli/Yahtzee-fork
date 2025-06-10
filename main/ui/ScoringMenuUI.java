package main.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.border.Border;
import javax.swing.*;

import main.ui.GameGraphics;
import main.ui.event.ScoreButtonAction;

public class ScoringMenuUI {
  public static JPanel container;

  public static JButton[] btnsScore;

  public static JLabel[] jLabels;

  public static JLabel totalScore;

  public static String[] labelIDs = {
      "Ones", "Twos", "Threes", "Fours", "Fives", "Sixes",
      "Three of a Kind", "Four of a Kind", "Full House", "Small Straight",
      "Large Straight", "Yahtzee", "Chance"
  };

  public static String[] buttonIDs = {
      "Score", "Score", "Score", "Score", "Score", "Score", "Score", 
      "Score", "Score", "Score", "Score", "Score", "Score"
  };

  public static void createScoringMenu() {
    container = new JPanel();

    container.setBounds(1300, 100, 250, 492);

    // Creates a temporary border around the scoreboard.
    Border b = BorderFactory.createLineBorder(Color.BLACK);

    GridLayout layout = new GridLayout(14, 2);

    container.setLayout(layout);

    btnsScore = new JButton[13];

    jLabels = new JLabel[14];

    for (int i = 0; i < 13; i++) {

      jLabels[i] = new JLabel(labelIDs[i]);
      btnsScore[i] = new JButton("Score");

      jLabels[i].setBorder(b);
      btnsScore[i].setBorder(b);

      jLabels[i].setHorizontalAlignment(JLabel.CENTER);
      btnsScore[i].setHorizontalAlignment(JLabel.CENTER);

      // Uncomment the line below to disable the button when it is pressed.
      btnsScore[i].addActionListener(new ScoreButtonAction(i));

      container.add(jLabels[i]);
      container.add(btnsScore[i]);
    }

    // Add the "Total" label
    
    JLabel scoreLabel = new JLabel("Total");

    // Placeholder 0
    totalScore = new JLabel("0");

    jLabels[13] = scoreLabel;

    jLabels[13].setBorder(b);
    totalScore.setBorder(b);

    jLabels[13].setHorizontalAlignment(JLabel.CENTER);
    totalScore.setHorizontalAlignment(JLabel.CENTER);

    container.add(scoreLabel);
    container.add(totalScore);

    container.setBorder(b);

    GameGraphics.frame.add(container);
  }

}
