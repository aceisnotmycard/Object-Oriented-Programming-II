package ru.nsu.ccfit.bogolepov.tetris.controller;

import ru.nsu.ccfit.bogolepov.tetris.model.Block;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockAdapter;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockFactory;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;
import ru.nsu.ccfit.bogolepov.tetris.view.TetrisView;

import javax.swing.*;


public class TetrisController {

    Field field;
    Field preview;
    BlockFactory factory;
    Block currentBlock;
    Block nextBlock;
    BlockAdapter fieldAdapter;
    BlockAdapter previewAdapter;
    TetrisView view;

    public TetrisController(int width, int height) {
        field = new Field(width, height);
        preview = new Field(5, 5);
        factory = new BlockFactory();
        currentBlock = factory.createRandomBlock();
        nextBlock = factory.createRandomBlock();
        fieldAdapter = new BlockAdapter(currentBlock, field, field.getWidth() / 2, 0);
        previewAdapter = new BlockAdapter(nextBlock, preview, preview.getWidth() / 2, 0);
    }

    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view = new TetrisView(field);
                view.run();
            }
        });
    }

    public Field getField() {
        return field;
    }

    public void makeStep() {
        if (!fieldAdapter.move(0, 1)) {
            int count = 0;
            for (int i = 0; i < field.getWidth(); i++) {
                if (field.getPointAt(i, 0).getType() != null) {
                    count++;
                }
            }
            if (count == field.getWidth()) {
                return;
            }
            field.clearFilledRows();
            currentBlock = nextBlock;
            fieldAdapter = new BlockAdapter(currentBlock, field, field.getWidth() / 2, 0);
            nextBlock = factory.createRandomBlock();
        }
    }

    public void moveBlockLeft() {
        fieldAdapter.move(-1, 0);
    }

    public void moveBlockRight() {
        fieldAdapter.move(1, 0);
    }

    public void fallBlock() {
        fieldAdapter.fall();
    }

    public void rotatePieceLeft() {
        fieldAdapter.rotateLeft();
    }

    public void rotatePieceRight() {
        fieldAdapter.rotateRight();
    }
}
