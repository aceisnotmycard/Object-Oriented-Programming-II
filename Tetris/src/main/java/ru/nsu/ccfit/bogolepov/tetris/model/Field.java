package ru.nsu.ccfit.bogolepov.tetris.model;

import java.util.Observable;

/**
 * Game field
 */
public class Field {
    private int height;
    private int width;

    private Pixel[][] field;

    public Field(int width, int height) {
        field = new Pixel[width][height];
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    public Pixel getPixelAt(int x, int y) {
        if (x < width && y  < height && x > -1 && y > -1) {
            return field[x][y];
        } else {
            return null;
        }
    }

    // returns number of cleared rows
    // TODO
    public int clearFilledRows() {
        int sum = 0;

        for (int y = 0; y < height; y++) {
            int filledPixels = 0;
            for (int x = 0; x < width; x++) {
                if (getPixelAt(x, y).getState() == Pixel.State.FELL) {
                    filledPixels++;
                }
             }
            if (filledPixels == width) {
                sum++;
            }
        }
        return sum;
    }
}
