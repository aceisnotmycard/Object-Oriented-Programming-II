package ru.nsu.ccfit.bogolepov.tetris.swingtris;

import ru.nsu.ccfit.bogolepov.tetris.controller.TetrisController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by aceisnotmycard on 4/28/15.
 */
public class MenuView extends JFrame {

    JButton newGameButton;
    JButton exitButton;
    JButton aboutButton;
    JButton scoresButton;

    public MenuView() {

        JPanel panel = new JPanel();

        newGameButton = new JButton("New game");
        scoresButton = new JButton("Scores");
        aboutButton = new JButton("About");
        exitButton = new JButton("Exit");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TetrisController controller = new TetrisController();
                controller.run();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionPane = new JOptionPane("Sergey Bogolepov, 2015");
                optionPane.show();
            }
        });

        panel.add(newGameButton);
        panel.add(scoresButton);
        panel.add(aboutButton);
        panel.add(exitButton);

        add(panel);
        setSize(150, 200);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
