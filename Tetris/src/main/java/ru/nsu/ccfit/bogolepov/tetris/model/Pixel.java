package ru.nsu.ccfit.bogolepov.tetris.model;

/**
 * Element of game field
 */
public class Pixel {
    public enum State {
        ACTIVE, FELL, FREE
    }

    private int x;
    private int y;

    private State state;

    public Pixel(int x, int y) {
        state = State.FREE;
        this.x = x;
        this.y = y;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
