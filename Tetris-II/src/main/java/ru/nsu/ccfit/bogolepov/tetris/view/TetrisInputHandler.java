package ru.nsu.ccfit.bogolepov.tetris.view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisInputHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT:
                break;
            case KeyEvent.VK_UP:
                break;
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_SPACE:
                break;
            case KeyEvent.VK_P:
                break;
        }
    }
}
