package view;

import ru.nsu.ccfit.bogolepov.tetris.model.Field;

import java.awt.*;

/**
 * Created by aceisnotmycard on 5/1/15.
 */
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
                drawSquare(g, j, i, field.getPointAt(j, i).getClass(), boardTop);
            }
        }
    }

}
