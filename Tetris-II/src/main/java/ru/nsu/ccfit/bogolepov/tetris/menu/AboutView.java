package ru.nsu.ccfit.bogolepov.tetris.menu;

import javax.swing.*;

public class AboutView extends JFrame {
    AboutView() {
        JTextArea textArea = new JTextArea("Sergey Bogolepov, NSU, 2015");
        add(textArea);
        setSize(300, 150);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
