package ru.nsu.ccfit.bogolepov.tetris.swingtris;

import ru.nsu.ccfit.bogolepov.tetris.model.Field;

import java.awt.*;

public class FieldView extends GameView {

    private Field field;

    public FieldView(int width, int height, Field field) {
        super(width, height);
        this.field = field;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - fieldHeight * squareHeight();

        for (int i = 0; i < fieldHeight; i++) {
            for (int j = 0; j < fieldWidth; j++) {
                drawSquare(g, j, i, field.getBlockTypeAt(j,i), boardTop);
            }
        }
    }

}
