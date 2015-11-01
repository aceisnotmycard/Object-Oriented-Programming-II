package ru.nsu.ccfit.bogolepov.chat.client;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.start(args[0], Integer.valueOf(args[1]), args[2]);
    }
}
