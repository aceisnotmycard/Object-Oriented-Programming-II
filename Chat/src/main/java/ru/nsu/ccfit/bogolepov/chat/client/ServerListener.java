package ru.nsu.ccfit.bogolepov.chat.client;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.Receiver;

import java.io.IOException;

public class ServerListener implements Runnable {

    private Receiver<Message<ClientContext>> receiver;
    private ClientContext context;

    public ServerListener(Receiver<Message<ClientContext>> receiver, ClientContext context) {
        this.receiver = receiver;
        this.context = context;
    }

    @Override
    public void run() {
        while (true) {
            try {
                receiver.receive().ifPresent(msg -> msg.exec(context));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
