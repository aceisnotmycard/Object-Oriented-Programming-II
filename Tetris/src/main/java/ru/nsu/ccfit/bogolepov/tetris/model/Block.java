package ru.nsu.ccfit.bogolepov.tetris.model;

import ru.nsu.ccfit.bogolepov.tetris.model.blocks.*;

import java.util.*;

public class Block {
    protected int blockSize;
    protected int[][] pattern;
    protected BlockType type;
    protected Field field;
    protected int x;
    protected int y;

    public BlockType getType() {
        return type;
    }

    public Block() {}

    public Block(Field field, int x, int y) {
        this.field = field;
        this.x = x;
        this.y = y;
        fillFieldAround(x, y, true);
    }

    public boolean move(int dx, int dy) {
        int newX = x + dx;
        int newY = y + dy;

        // clear field for a minute
        fillFieldAround(x, y, false);

        // is any free space on the new position
        if (!isFilledAround(newX, newY)) {
            // Well, nice.
            fillFieldAround(newX, newY, true);
            x = newX;
            y = newY;
            return true;
        } else {
            // At least, we tried
            fillFieldAround(x, y, true);
            return false;
        }
    }

    public boolean rotateLeft() {
        int[][] oldPattern = pattern;
        int[][] newPattern = new int[blockSize][2];

        fillFieldAround(x, y, false);
        for (int i = 0; i < blockSize; i++) {
            newPattern[i][0] = pattern[i][1];
            newPattern[i][1] = -pattern[i][0];
        }
        pattern = newPattern;
        if(isFilledAround(x, y)) {
            pattern = oldPattern;
            return false;
        } else {
            fillFieldAround(x, y, true);
            return true;
        }
    }

    public boolean rotateRight() {
        int[][] oldPattern = pattern;
        int[][] newPattern = new int[blockSize][2];

        fillFieldAround(x, y, false);
        for (int i = 0; i < blockSize; i++) {
            newPattern[i][0] = -pattern[i][1];
            newPattern[i][1] = pattern[i][0];
        }
        pattern = newPattern;
        if(isFilledAround(x, y)) {
            pattern = oldPattern;
            return false;
        } else {
            fillFieldAround(x, y, true);
            return true;
        }
    }

    public void fall() {
        while(move(0, 1));
    }

    private boolean isFilledAround(int x, int y) {
        for (int i = 0; i < blockSize; i++) {
            int tracingX = x + pattern[i][0];
            int tracingY = y + pattern[i][1];
            if ( tracingX >= field.getWidth() || tracingY >= field.getHeight() || tracingX < 0 || tracingY < 0) {
                return true;
            }
            if (field.getBlockTypeAt(x + pattern[i][0], y + pattern[i][1]) != null) {
                return true;
            }
        }
        return false;
    }

    private void fillFieldAround(int x, int y, boolean value) {
        for (int i = 0; i < blockSize; i++) {
            if(value) {
                field.setBlockTypeAt(x + pattern[i][0], y + pattern[i][1], type);
            } else {
                field.setBlockTypeAt(x + pattern[i][0], y + pattern[i][1], null);
            }
        }
    }

    // TODO: Аф
    public static Block generateRandomBlock(Field field, int x, int y) {
        Block[] possibleBlocks = new Block[7];
        possibleBlocks[0] = new CubeBlock(field, x, y);
        possibleBlocks[1] = new LBlock(field, x, y);
        possibleBlocks[2] = new LineBlock(field, x, y);
        possibleBlocks[3] = new MirroredLBlock(field, x, y);
        possibleBlocks[4] = new SBlock(field, x, y);
        possibleBlocks[5] = new TBlock(field, x, y);
        possibleBlocks[6] = new ZBlock(field, x, y);

        Random r = new Random();
        int z = Math.abs(r.nextInt()) % 7;

        return possibleBlocks[z];
    }
}