package ru.nsu.ccfit.bogolepov.tetris.controller;

import ru.nsu.ccfit.bogolepov.tetris.event.Event;
import ru.nsu.ccfit.bogolepov.tetris.event.EventQueue;
import ru.nsu.ccfit.bogolepov.tetris.model.Block;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockAdapter;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockFactory;
import ru.nsu.ccfit.bogolepov.tetris.model.Field;
import ru.nsu.ccfit.bogolepov.tetris.view.TetrisView;

import javax.swing.*;


public class TetrisController {

    private Field field;
    private Field preview;
    private BlockFactory factory;
    private Block currentBlock;
    private Block nextBlock;
    private BlockAdapter fieldAdapter;
    private BlockAdapter previewAdapter;
    private TetrisView view;
    private EventQueue eventQueue;

    private boolean isEnded;

    public TetrisController(int width, int height) {
        field = new Field(width, height);
        preview = new Field(5, 5);
        factory = new BlockFactory();
        currentBlock = factory.createRandomBlock();
        nextBlock = factory.createRandomBlock();
        fieldAdapter = new BlockAdapter(currentBlock, field, field.getWidth() / 2, 0);
        previewAdapter = new BlockAdapter(nextBlock, preview, preview.getWidth() / 2, 0);
        eventQueue = new EventQueue();
    }

    public void run() {
        isEnded = false;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                view = new TetrisView(field, preview, eventQueue);
                view.run();
            }
        });

        while (!isEnded) {
            if (eventQueue.hasEvent()) {
                Event event  = eventQueue.getEvent();
                switch (event) {
                    case MOVE_LEFT:
                        moveBlockLeft();
                        break;
                    case MOVE_RIGHT:
                        moveBlockRight();
                        break;
                    case ROTATE_LEFT:
                        rotateBlockLeft();
                        break;
                    case ROTATE_RIGHT:
                        rotateBlockRight();
                        break;
                    case FALL:
                        fallBlock();
                        break;
                    case GAME_STEP:
                        makeStep();
                        break;
                }
            }
        }
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
            previewAdapter.setBlock(nextBlock);
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

    public void rotateBlockLeft() {
        fieldAdapter.rotateLeft();
    }

    public void rotateBlockRight() {
        fieldAdapter.rotateRight();
    }
}
