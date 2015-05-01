package ru.nsu.ccfit.bogolepov.tetris.swingtris;

import ru.nsu.ccfit.bogolepov.tetris.model.Block;
import ru.nsu.ccfit.bogolepov.tetris.model.BlockType;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {

    int fieldHeight;
    int fieldWidth;

    GameView(int width, int height) {
        fieldHeight = height;
        fieldWidth = width;
    }

    int squareHeight() {
        return (int) getSize().getHeight() / fieldHeight;
    }

    int squareWidth() {
        return (int) getSize().getWidth() / fieldWidth;
    }

    void drawSquare(Graphics g, int j, int i, BlockType type, int boardTop) {
        int x = j * squareWidth();
        int y = i * squareHeight();

        Color color = colorPicker(type);

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }

    private Color colorPicker(BlockType type) {
        Color colors[] = { new Color(204, 102, 102),
                new Color(102, 204, 102), new Color(102, 102, 204),
                new Color(204, 204, 102), new Color(204, 102, 204),
                new Color(102, 204, 204), new Color(218, 170, 0)
        };
        return type == null?  new Color(0,0,0) : colors[type.ordinal()];
    }
}
