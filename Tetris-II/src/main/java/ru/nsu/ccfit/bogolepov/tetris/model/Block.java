package ru.nsu.ccfit.bogolepov.tetris.model;

/**
 * Interface for Tetrominoes
 *
 */
public interface Block {
    /**
     *
     * @return relative coordinates of parts
     */
    int[][] getPattern();

    /**
     *
     * @param newPattern – new structure of block
     */
    void setPattern(int[][] newPattern);

    /**
     *
     * @return number of parts in block
     */
    int getSize();
}
