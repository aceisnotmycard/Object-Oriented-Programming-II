package ru.nsu.ccfit.bogolepov.chat.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.Receiver;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

import java.io.IOException;

public class ServerListener implements Runnable {

    private Logger logger = LogManager.getLogger(getClass());

    private Receiver receiver;
    private ClientContext context;
    private ServerMessage message;

    public ServerListener(Receiver receiver, ClientContext context) {
        this.receiver = receiver;
        this.context = context;
    }

    @Override
    public void run() {
        while (true) {
            try {
                message = (ServerMessage) receiver.receive();
                if (message != null) {
                    logger.info("Received message!");
                    message.exec(context);
                }
            } catch (ClassNotFoundException e) {
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error(e.getMessage());
                break;
            }
        }
    }
}
