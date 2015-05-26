package ru.nsu.ccfit.bogolepov.tetris;

import ru.nsu.ccfit.bogolepov.tetris.controller.TetrisController;

public class Main {
    public static void main(String[] args) {
        (new TetrisController(10, 22)).run();
    }
}
