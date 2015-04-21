package ru.nsu.ccfit.bogolepov.tetris.controller;

import ru.nsu.ccfit.bogolepov.tetris.model.Field;
import ru.nsu.ccfit.bogolepov.tetris.model.Piece;
import ru.nsu.ccfit.bogolepov.tetris.model.Pixel;
import ru.nsu.ccfit.bogolepov.tetris.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by aceisnotmycard on 4/22/15.
 */
public class TetrisConroller implements ActionListener {

    private boolean isFallingFinished;
    private View view;
    private Field field;
    private Piece currentPiece;

    public TetrisConroller(View view) {
        this.view = view;
        view.setField(field);
        currentPiece = new Piece(field);
        currentPiece.generateRandomPiece(field.getWidth()/2 + 1, 0);
    }

    // Main cycle
    public void makeStep() {
        if (isFallingFinished) {
            isFallingFinished = false;
            currentPiece.makeFell();
            currentPiece.generateRandomPiece(field.getWidth()/2 + 1, 0);
        } else {
            currentPiece.move(0, 1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        makeStep();

    }
}
