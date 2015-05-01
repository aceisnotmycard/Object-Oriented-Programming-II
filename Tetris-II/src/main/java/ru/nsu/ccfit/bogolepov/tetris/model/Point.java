package ru.nsu.ccfit.bogolepov.tetris.model;

/**
 * Basic building block of game field
 */
public class Point {
    int x;
    int y;
    Class<?> type;

    Point(int x, int y, Class<?> type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = null;
    }

    void setType(Class<?> type) {
        this.type = type;
    }

    Class<?> getType() {
        return type;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
