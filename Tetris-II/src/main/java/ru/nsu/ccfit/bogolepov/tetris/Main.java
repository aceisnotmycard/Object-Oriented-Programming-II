package ru.nsu.ccfit.bogolepov.tetris;

import ru.nsu.ccfit.bogolepov.tetris.controller.MenuController;
import ru.nsu.ccfit.bogolepov.tetris.menu.MenuView;

public class Main {
    public static void main(String[] args) {
        MenuController controller = new MenuController();
        controller.run();
    }
}
