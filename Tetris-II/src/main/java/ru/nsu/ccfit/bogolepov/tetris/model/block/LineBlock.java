package ru.nsu.ccfit.bogolepov.tetris.model.block;

import ru.nsu.ccfit.bogolepov.tetris.model.Block;

/**
 * Created by aceisnotmycard on 5/1/15.
 */
public class LineBlock implements Block {

    int[][] pattern = {
            {-1, 0}, {0, 0}, {1, 0}, {2, 0}
    };

    @Override
    public int[][] getPattern() {
        return pattern;
    }

    @Override
    public void setPattern(int[][] newPattern) {
        this.pattern = newPattern;
    }

    @Override
    public int getSize() {
        return pattern.length;
    }
}
