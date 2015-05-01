package ru.nsu.ccfit.bogolepov.tetris.model;

import java.util.ArrayList;
import java.util.List;


public class BlockAdapter {
    Block block;
    Field field;
    ArrayList<Point> points;
    int centerX;
    int centerY;

    /*
    Places block on a field at (x,y)
     */
    public BlockAdapter(Block block, Field field, int x, int y) {
        this.field = field;
        this.block = block;
        centerX = x;
        centerY = y;

        placeBlockAt(x, y);
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void rotateLeft() {
        int[][] newPattern = new int[getBlock().getSize()][2];
        int[][] oldPattern = getBlock().getPattern().clone();
        for (int i = 0; i < getBlock().getSize(); i++) {
            newPattern[i][0] = -getBlock().getPattern()[i][1];
            newPattern[i][1] = getBlock().getPattern()[i][0];
        }
        block.setPattern(newPattern);
        if (checkCollisionsAt(centerX, centerY)) {
            placeBlockAt(centerX, centerY);
        } else {
            block.setPattern(oldPattern);
        }
    }

    public void rotateRight() {
        int[][] newPattern = new int[getBlock().getSize()][2];
        int[][] oldPattern = getBlock().getPattern().clone();
        for (int i = 0; i < getBlock().getSize(); i++) {
            newPattern[i][0] = getBlock().getPattern()[i][1];
            newPattern[i][1] = -getBlock().getPattern()[i][0];
        }
        block.setPattern(newPattern);
        if (checkCollisionsAt(centerX, centerY)) {
            placeBlockAt(centerX, centerY);
        } else {
            block.setPattern(oldPattern);
        }
    }

    public void move(int dx, int dy) {
        int newX = centerX + dx;
        int newY = centerY + dy;
        if (checkCollisionsAt(newX, newY)) {
            for (Point point : points) {
                point.setType(null);
            }
            field.updatePoints(points);
            placeBlockAt(newX, newY);
        }
    }

    // returns true if there are not points at specified positions
    private boolean checkCollisionsAt(int x, int y) {

        for (int[] point : block.getPattern()) {
            if (!field.isFineX(x + point[0]) || !field.isFineY(y + point[1])) {
                return false;
            }
            if (field.getPointAt(x + point[0], y + point[1]).getType() != null) {
                return false;
            }
        }
        return true;
    }

    private void placeBlockAt(int x, int y) {
        points = new ArrayList<Point>();
        for (int[] point : block.getPattern()) {
            points.add(new Point(x + point[0], y + point[1], block.getClass()));
        }
        field.updatePoints(points);
    }
}
