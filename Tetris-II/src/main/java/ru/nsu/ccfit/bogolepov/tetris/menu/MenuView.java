package ru.nsu.ccfit.bogolepov.tetris.menu;

import ru.nsu.ccfit.bogolepov.tetris.controller.TetrisController;
import ru.nsu.ccfit.bogolepov.tetris.event.EventQueue;
import ru.nsu.ccfit.bogolepov.tetris.event.MenuEvent;

import javax.swing.*;

public class MenuView extends JFrame implements Runnable {
    JButton newGameButton;
    JButton aboutButton;
    JButton scoresButton;
    JButton exitButton;

    EventQueue<MenuEvent> eventQueue;

    public MenuView(EventQueue<MenuEvent> eventQueue) {

        this.eventQueue = eventQueue;

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> {
            eventQueue.addEvent(MenuEvent.RUN_GAME);
        });

        aboutButton = new JButton("About");
        aboutButton.addActionListener(e -> {
            AboutView aboutView = new AboutView();
        });

        scoresButton = new JButton("Scores");
        scoresButton.addActionListener(e -> {
            ScoresView scoresView = new ScoresView();
        });

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.add(newGameButton);
        menuPanel.add(scoresButton);
        menuPanel.add(aboutButton);
        menuPanel.add(exitButton);

        add(menuPanel);

        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);

    }

    @Override
    public void run() {
        setVisible(true);
    }
}
