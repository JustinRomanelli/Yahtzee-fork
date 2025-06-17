package main;

import java.util.Scanner;

import main.logic.Scoresheet;
import main.logic.DiceCup;
import main.logic.Player;
import main.logic.Round;

import main.ui.GameGraphics;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("", new Scoresheet(new DiceCup()));

        Scanner scanner = new Scanner(System.in);

        Round round = new Round(scanner, p1);

        // Loop while true, but break when p1 runs out of turns
        // while (true) {
            round.takeTurn();
        // }
    }
}

