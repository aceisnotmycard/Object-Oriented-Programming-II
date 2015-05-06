package ru.nsu.ccfit.bogolepov.tetris.controller;

import ru.nsu.ccfit.bogolepov.tetris.event.EventQueue;
import ru.nsu.ccfit.bogolepov.tetris.event.MenuEvent;
import ru.nsu.ccfit.bogolepov.tetris.menu.MenuView;


public class MenuController {

    EventQueue<MenuEvent> eventQueue;
    boolean isRunning;

    public MenuController() {
        eventQueue = new EventQueue<>();
    }

    public void run() {
        isRunning = true;
//        Thread thread = new Thread(new MenuView(eventQueue));
//        thread.run();
        (new MenuView(eventQueue)).run();
        while (isRunning) {
            MenuEvent event = eventQueue.getEvent();
            if (event != null) {
                switch (event) {
                    case RUN_GAME:
                        TetrisController controller = new TetrisController(10, 22);
                        controller.run();

                }
            }
        }
    }
}
