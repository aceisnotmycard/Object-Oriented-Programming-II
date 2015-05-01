package ru.nsu.ccfit.bogolepov.tetris.menu;

import javax.swing.*;

/**
 * Created by aceisnotmycard on 5/1/15.
 */
public class MenuView extends JFrame {
    JButton newGameButton;
    JButton aboutButton;
    JButton scoresButton;
    JButton exitButton;

    MenuView() {
        setSize(150, 300);

        newGameButton = new JButton("New Game");
        aboutButton = new JButton("About");
        scoresButton = new JButton("Scores");
        exitButton = new JButton("Exit");

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.LINE_AXIS));
        menuPanel.add(newGameButton);
        menuPanel.add(scoresButton);
        menuPanel.add(aboutButton);
        menuPanel.add(exitButton);
    }
}
