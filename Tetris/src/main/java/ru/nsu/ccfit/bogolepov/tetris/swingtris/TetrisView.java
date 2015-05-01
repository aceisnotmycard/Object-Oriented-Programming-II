package ru.nsu.ccfit.bogolepov.tetris.swingtris;

import ru.nsu.ccfit.bogolepov.tetris.controller.TetrisController;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;
import ru.nsu.ccfit.bogolepov.tetris.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;


public class TetrisView extends JFrame implements View, ActionListener {
    FieldView gameView;
    TetrisController controller;
    Timer timer;

    public static final int DEFAULT_DELAY = 600;
    int delay = DEFAULT_DELAY;

    public TetrisView(TetrisController controller, Field field) {
        this.controller = controller;
        gameView = new FieldView(10, 22, field);
        //nextPieceView = new NextPieceView();
        add(gameView);
        timer = new Timer(delay, this);
        setSize(200, 400);
        setTitle("Tetris");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_LEFT:
                        controller.moveBlockLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        controller.moveBlockRight();
                        break;
                    case KeyEvent.VK_UP:
                        controller.rotatePieceLeft();
                        break;
                    case KeyEvent.VK_DOWN:
                        controller.rotatePieceRight();
                        break;
                    case KeyEvent.VK_SPACE:
                        controller.fallBlock();
                }
                gameView.repaint();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.makeStep();
        repaint();
    }

    public void run() {
        setLocationRelativeTo(null);
        setVisible(true);
        timer.start();
    }

    @Override
    public void update(Observable o, Object arg) {
        Integer difficulty = (Integer) arg;
        delay /= difficulty;
        timer.setDelay(delay);
        gameView.repaint();
    }
}
