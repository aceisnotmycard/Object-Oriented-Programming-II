package ru.nsu.ccfit.bogolepov.tetris.controller;

import ru.nsu.ccfit.bogolepov.tetris.model.Block;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockAdapter;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockFactory;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;
import view.TetrisView;

/**
 * Created by aceisnotmycard on 4/28/15.
 */
public class TetrisController {

    Field field;
    Field preview;
    BlockFactory factory;
    Block currentBlock;
    Block nextBlock;
    BlockAdapter fieldAdapter;
    BlockAdapter previewAdapter;

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
    }

    public void makeStep() {
        if (!fieldAdapter.move(0, 1)) {

        }
    }

    public void moveBlockLeft() {
        fieldAdapter.move(-1, 0);
    }

    public void moveBlockRight() {
        fieldAdapter.move(1, 0);
    }

    public void fallBlock() {
        fieldAdapter.rotateLeft();
    }

    public void rotatePieceLeft() {
        fieldAdapter.rotateRight();
    }

    public void rotatePieceRight() {
        //currentBlock.rotateRight();
    }
}
