package ru.nsu.ccfit.bogolepov.tetris.model;

import java.util.ArrayList;

/**
 * Created by aceisnotmycard on 4/22/15.
 */
public class Piece {
    ArrayList<Pixel> currentPixels;
    Field field;

    public Piece(Field field) {
        this.field = field;
    }

    public void makeFell() {
        for (Pixel pixel : currentPixels) {
            field.getPixelAt(pixel.getX(), pixel.getY()).setState(Pixel.State.FELL);
        }
    }

    public void generateRandomPiece(int x, int y) {
        ArrayList<Pixel> newPixels = new ArrayList<>();
        newPixels.add(field.getPixelAt(x, y));
        newPixels.add(field.getPixelAt(x+1, y));
        newPixels.add(field.getPixelAt(x+1, y+1));
        newPixels.add(field.getPixelAt(x, y+1));
        for(Pixel pixel : newPixels) {
            pixel.setState(Pixel.State.ACTIVE);
        }
        currentPixels = newPixels;
    }

    //TODO: Refactor
    // Move piece if possible
    public void move(int dx, int dy) {
        ArrayList<Pixel> newPixels = new ArrayList<>();

        // Check collisions
        for (Pixel pixel : currentPixels) {
            int newX = pixel.getX() + dx;
            int newY = pixel.getY() + dy;
            if (field.getPixelAt(newX, newY).getState() == Pixel.State.FELL) {
                return;
            }
            newPixels.add(field.getPixelAt(newX, newY));
        }
        for (Pixel pixel : currentPixels) {
            int newX = pixel.getX() + dx;
            int newY = pixel.getY() + dy;
            field.getPixelAt(pixel.getX(), pixel.getY()).setState(Pixel.State.FREE);
            field.getPixelAt(newX, newY).setState(Pixel.State.ACTIVE);
        }
        currentPixels = newPixels;
    }
}
