package ru.nsu.ccfit.bogolepov.tetris.view;

import ru.nsu.ccfit.bogolepov.tetris.event.Event;
import ru.nsu.ccfit.bogolepov.tetris.event.EventQueue;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class TetrisView extends JFrame implements ActionListener {
    private FieldView fieldView;
    private FieldView previewView;
    private EventQueue eventQueue;
    private Timer timer;

    public TetrisView(Field field, Field preview, EventQueue eventQueue) {
        this.eventQueue = eventQueue;

        fieldView = new FieldView(field);
        fieldView.setSize(200, 440);
        field.addObserver(new FieldObserver(fieldView));

        previewView = new FieldView(preview);
        previewView.setSize(100, 100);
        preview.addObserver(new FieldObserver(previewView));

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.LINE_AXIS));

        gamePanel.add(fieldView);
        gamePanel.add(previewView);
        add(gamePanel);
        setSize(300, 440);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        timer = new Timer(400, this);
        addKeyListener(new TetrisInputHandler(eventQueue));
    }

    public void run() {
        setLocationRelativeTo(null);
        setVisible(true);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        eventQueue.addEvent(Event.GAME_STEP);
    }

    private class FieldObserver implements Observer {
        FieldView view;

        FieldObserver(FieldView view) {
            this.view = view;
        }
        @Override
        public void update(Observable o, Object arg) {
            view.repaint();
        }
    }
}
