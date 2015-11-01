package ru.nsu.ccfit.bogolepov.chat.server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(Integer.valueOf(args[0]));
        server.start();
    }
}
