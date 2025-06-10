package main.ui;

// Libraries for displaying the window, buttons, images, etc.
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

// Custom implementations for an image, button and scoring menu, respectively
import main.logic.Die;
import main.ui.ScoringMenuUI;
import main.ui.event.HoldButtonAction;
import main.ui.event.RollButtonAction;

/*
 * Runs the graphics side of the program.
 * The majority of the code runs in the show() method. The rest are helper methods.
 */
public class GameGraphics {

    // The frame that contains the UI.
    public static JFrame frame;

    public static Die[] allDice;
  
    public static JButton[] buttonArray = new JButton[5];

    private static JButton rollButton;

    // The JLabels for the number of rolls and current round number.
    public static JLabel rollsLabel = new JLabel("");

    public static JLabel roundLabel = new JLabel("");

    // Current number of rolls and the current round number
    public static int numRolls = 1;

    private static int numRounds = 1;

    // All of the image paths
    // NOTE: Only DIE_ONE_PATH currently has an image!
    private static final String DIE_ONE_PATH = "./imgs/die_1.png";
    // private static final String DIE_TWO_PATH = "./imgs/die_2.png";
    // private static final String DIE_THREE_PATH = "./imgs/die_3.png";
    // private static final String DIE_FOUR_PATH = "./imgs/die_4.png";
    // private static final String DIE_FIVE_PATH = "./imgs/die_5.png";
    // private static final String DIE_SIX_PATH = "./imgs/die_6.png";

    /* 
     * Shows the UI.
     * Currently a work in progress. 
     */
    public static void show() {

        // Sets up the JFrame (window that holds the graphics)
        frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.setSize(1600, 900);

        // This allows us to place elements absolutely, so that it does not default to a grid-like shape.
        // May be changed in the future, but for early in the project, this works.
        frame.setLayout(null);

        allDice = new Die[5];

        // Draws the dice, with the formate IMG_FILE_PATH, x position, y position
        GameGraphics.drawDie(DIE_ONE_PATH, 175, 150, 0);
        GameGraphics.drawDie(DIE_ONE_PATH, 400, 150, 1);
        GameGraphics.drawDie(DIE_ONE_PATH, 625, 150, 2);
        GameGraphics.drawDie(DIE_ONE_PATH, 850, 150, 3);
        GameGraphics.drawDie(DIE_ONE_PATH, 1075, 150, 4);

        // Draws the hold buttons under the dice, with the x and y coordinates, then the button index.
        // The button index is currently used to identify which button is pressed, through a JButton[] array.
        GameGraphics.drawHoldButton(200, 350, 0);
        GameGraphics.drawHoldButton(425, 350, 1);
        GameGraphics.drawHoldButton(650, 350, 2);
        GameGraphics.drawHoldButton(875, 350, 3);
        GameGraphics.drawHoldButton(1100, 350, 4);


        GameGraphics.drawRollButton(650, 500, "Roll Dice");

        GameGraphics.drawRoundNumber(50, 50);
        GameGraphics.drawNumberOfRolls(50, 70);

        // Create the scoreboard UI
        ScoringMenuUI.createScoringMenu();

        // Informally creates the frame, so that the user can see it.
        frame.setVisible(true);
    }

    /*
     * Draws the die to the screen.
     * Parameters: path is the path to the image to draw,
     *             x is the x coordinate to draw, from the top left corner,
     *             y is the y coordinate to draw, from the top-left corner.
     * Returns: None
     */
    public static void drawDie(String path, int x, int y, int btnIndex) {
        Die img = new Die(path, btnIndex);

        allDice[btnIndex] = img;

        JLabel imgLabel = new JLabel(img);

        // Parameters for setBounds(): X-pos, Y-pos, Width, Height
        imgLabel.setBounds(x, y, 150, 150);

        frame.add(imgLabel);
    }

    /*
     * Draws the "Hold" Button to the screen, currently under the die.
     * Parameters: x is the x coordinate to draw, from the top left corner,
     *             y is the y coordinate to draw, from the top-left corner.
     *             btnIndex is a number [0-4] (inclusive) that allows the program to determine what button is being used.
     * Returns: None
     */
    private static void drawHoldButton(int x, int y, int btnIndex) {
        JButton button = new JButton("Hold");

        // The String in the first parameter of HoldButtonAction is what is printed when the button is pressed.
        button.addActionListener(new HoldButtonAction(btnIndex));

        button.setBounds(x, y, 100, 20);

        frame.add(button);

        buttonArray[btnIndex] = button;
    }

    // This creates the button that allows the user to roll the dice. 
    public static void drawRollButton(int x, int y, String btnText) {
        GameGraphics.rollButton = new JButton(btnText);
        GameGraphics.rollButton.setBounds(x, y, 100, 50);
        GameGraphics.rollButton.setBackground(Color.CYAN);
        GameGraphics.rollButton.addActionListener(new RollButtonAction());
        GameGraphics.rollButton.setEnabled(true);
        frame.add(GameGraphics.rollButton);
    }

    // This creates the text that lets the user what round number it is.
    public static void drawRoundNumber(int x, int y) {
        GameGraphics.roundLabel = new JLabel("Round: " + GameGraphics.numRounds + "/14");
        GameGraphics.roundLabel.setBounds(x, y, 100, 20);
        frame.add(GameGraphics.roundLabel);
   }

    // This creates the text that tells the user how many rolls they have left.
    public static void drawNumberOfRolls(int x, int y) {
        GameGraphics.rollsLabel = new JLabel("Roll " + GameGraphics.numRolls + "/3");
        GameGraphics.rollsLabel.setBounds(x, y, 100, 20);
        frame.add(GameGraphics.rollsLabel);
    }
    
    // This is the function that changes the color of the button depending on if the dice is being held or not. 
    public static void colorHoldButton(boolean isHeld, int btnIndex) {
        
        JButton current = buttonArray[btnIndex];

        if (isHeld) {
            current.setBackground(Color.GREEN);
            current.setText("Held");
        } else {
            current.setBackground(null);
            current.setText("Hold");
        }
        
    }

    // Refreshes the round, so that it is incremented.
    // NOTE: Later, these should make sure that these numbers do not exceed their limits (14 and 3, respectively)
    public static void incrementRound() {
        GameGraphics.numRounds++;

        // If all rounds have been used, close the app.
        // TODO: Show a final score screen
        if (GameGraphics.numRounds > 13) {
            frame.setVisible(false);

            // This appears to be a messy way to do this, so this will have to be worked on.
            System.exit(0);
        }

        // Remove and recreate roundLabel
        frame.remove(GameGraphics.roundLabel);

        GameGraphics.drawRoundNumber(50, 50);

        // Redraw the roll button
        frame.remove(GameGraphics.rollButton);

        GameGraphics.drawRollButton(650, 500, "Roll Dice");

        // Reset the roll count
        GameGraphics.numRolls = 1;

        // IMPORTANT: Make sure to run this whenever updating a piece of the UI!!
        frame.update(frame.getGraphics());
    }

    public static void incrementRolls() {
        GameGraphics.numRolls++;

        // "Gray out" the Roll Dice button when all rolls have been used up.
        if (GameGraphics.numRolls > 2) {
            frame.remove(GameGraphics.rollButton);

            GameGraphics.drawRollButton(650, 500, "No more rolls");
        
            GameGraphics.rollButton.setEnabled(false);
        }
        
        // Remove and recreate rollsLabel
        frame.remove(GameGraphics.rollsLabel);

        GameGraphics.drawNumberOfRolls(50, 70);

        // IMPORTANT: Make sure to run this whenever updating a piece of the UI!!
        frame.update(frame.getGraphics());
    }
}
