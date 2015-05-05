package ru.nsu.ccfit.bogolepov.tetris.view;

import ru.nsu.ccfit.bogolepov.tetris.event.TetrisEvent;
import ru.nsu.ccfit.bogolepov.tetris.event.EventQueue;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisInputHandler extends KeyAdapter {

    EventQueue eventQueue;

    public TetrisInputHandler(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                eventQueue.addEvent(TetrisEvent.MOVE_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                eventQueue.addEvent(TetrisEvent.MOVE_RIGHT);
                break;
            case KeyEvent.VK_UP:
                eventQueue.addEvent(TetrisEvent.ROTATE_RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                eventQueue.addEvent(TetrisEvent.ROTATE_LEFT);
                break;
            case KeyEvent.VK_SPACE:
                eventQueue.addEvent(TetrisEvent.FALL);
                break;
            case KeyEvent.VK_P:
                break;
        }
    }
}
