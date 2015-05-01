package ru.nsu.ccfit.bogolepov.tetris.model;

/**
 * Game field
 */
public class Field{
    private int height;
    private int width;

    // true - point on field is filled
    // false - it's free
    private BlockType[][] blockType;


    public Field(int width, int height) {
        blockType = new BlockType[width][height];
        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                blockType[i][j] = null;
            }
        }
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setBlockTypeAt(int x, int y, BlockType type) {
        blockType[x][y] = type;
    }

    public BlockType getBlockTypeAt(int x, int y) {
        return blockType[x][y];
    }

    public boolean isFilledAt(int x, int y) {
        if (x < width && y  < height && x > -1 && y > -1) {
            return getBlockTypeAt(x, y) != null;
        } else {
            return true;
        }
    }

    // also returns number of cleared rows
    // TODO
    public int clearFilledRows() {
        int sum = 0;

        for (int y = 0; y < height; y++) {
            int filledPixels = 0;
            for (int x = 0; x < width; x++) {
                if (isFilledAt(x, y)) {
                    filledPixels++;
                }
             }
            if (filledPixels == width) {
                sum++;
                for (int t = y-1; t >= 0; t--) {
                    for (int x = 0; x < width; x++) {
                        if (t == 0) {
                            blockType[x][t] = null;
                        } else {
                            blockType[x][t+1] = blockType[x][t];
                            System.err.println(t);
                        }
                    }
                }
            }
        }
        return sum;
    }
}
