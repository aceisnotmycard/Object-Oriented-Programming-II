package ru.nsu.ccfit.bogolepov.chat.server;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server(6000);
        server.start();
    }
}
