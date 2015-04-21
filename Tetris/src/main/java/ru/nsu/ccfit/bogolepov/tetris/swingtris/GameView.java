package ru.nsu.ccfit.bogolepov.tetris.swingtris;

import ru.nsu.ccfit.bogolepov.tetris.controller.TetrisConroller;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;
import ru.nsu.ccfit.bogolepov.tetris.view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameView extends JPanel implements View {

    private Timer timer;
    private TetrisConroller conroller;
    private Field field;

    public GameView() {
        conroller = new TetrisConroller(this);
        setFocusable(true);
        timer = new Timer(400, conroller);
        timer.start();
    }

    @Override
    public void setField(Field field)  {

    }

    @Override
    public void update() {

    }
}
