package ru.nsu.ccfit.bogolepov.tetris.menuview;

import javax.swing.*;

public class AboutView extends JFrame {
    public AboutView() {
        JTextArea textArea = new JTextArea("Sergey Bogolepov, NSU, 2015");
        add(textArea);
        setSize(300, 150);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
