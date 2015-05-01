package ru.nsu.ccfit.bogolepov.tetris.controller;


import ru.nsu.ccfit.bogolepov.tetris.model.Block;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;
import ru.nsu.ccfit.bogolepov.tetris.swingtris.TetrisView;
import ru.nsu.ccfit.bogolepov.tetris.view.View;

import java.util.Observable;


public class TetrisController extends Observable {

    private View view;
    private Field field;
    private Block currentBlock;
    int score;

    public TetrisController() {
        field = new Field(10, 22);
        currentBlock = Block.generateRandomBlock(field, field.getWidth() / 2 + 1, 0);
        view = new TetrisView(this, field);
    }

    public void run() {
        score = 0;
        view.run();
    }

    // Main cycle
    public void makeStep() {
        if (!currentBlock.move(0,1)) {
            for (int x = 0; x < field.getWidth(); x++) {
                if (field.isFilledAt(x, 0)) {
                    //exit
                    return;
                }
            }
            int clearedRows = field.clearFilledRows();
            score += clearedRows*clearedRows;
            currentBlock = Block.generateRandomBlock(field, field.getWidth() / 2 + 1, 0);
        }
        setChanged();
        notifyObservers();
    }

    public void moveBlockLeft() {
        currentBlock.move(-1, 0);
    }

    public void moveBlockRight() {
        currentBlock.move(1, 0);
    }

    public void fallBlock() {
        currentBlock.fall();
    }

    public void rotatePieceLeft() {
        currentBlock.rotateLeft();
    }

    public void rotatePieceRight() {
        currentBlock.rotateRight();
    }

}
