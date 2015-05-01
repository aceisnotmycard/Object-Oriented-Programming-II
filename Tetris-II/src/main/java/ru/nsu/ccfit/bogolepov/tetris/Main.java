package ru.nsu.ccfit.bogolepov.tetris;

import ru.nsu.ccfit.bogolepov.tetris.controller.TetrisController;

/**
 * Created by aceisnotmycard on 5/1/15.
 */
public class Main {
    public static void main(String[] args) {
        TetrisController controller = new TetrisController(10, 22);
        controller.run();
    }
}
