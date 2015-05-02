package ru.nsu.ccfit.bogolepov.tetris.view;

import ru.nsu.ccfit.bogolepov.tetris.model.Field;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class TetrisView extends JFrame implements ActionListener {
    FieldView fieldView;
    Timer timer;

    public TetrisView(Field field) {
        field.addObserver(new FieldObserver());
        fieldView = new FieldView(field);
        add(fieldView);
        setSize(200, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        timer = new Timer(400, this);
        addKeyListener(new TetrisInputHandler());
    }

    public void run() {
        setLocationRelativeTo(null);
        setVisible(true);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class FieldObserver implements Observer {
        @Override
        public void update(Observable o, Object arg) {
            fieldView.repaint();
        }
    }
}
