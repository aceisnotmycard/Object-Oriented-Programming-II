package ru.nsu.ccfit.bogolepov.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableReceiver;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableTransmitter;
import ru.nsu.ccfit.bogolepov.chat.messaging.server_messages.ServerTextMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler extends Thread {

    Logger logger = LogManager.getLogger(getClass());

    private RequestHandlerContext context;
    private SerializableTransmitter transmitter;
    private SerializableReceiver receiver;
    private ClientMessage message;

    private boolean isServing;

    public RequestHandler(Socket socket, Server server) {
        context = new RequestHandlerContext(server);
        try {
            transmitter = new SerializableTransmitter(new ObjectOutputStream(socket.getOutputStream()));
            receiver = new SerializableReceiver(new ObjectInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Void sendMessage(String message) {
        logger.trace(getClass().getSimpleName() + "::sendMessage");
        transmitter.send(new ServerTextMessage(message));
        return null;
    }

    public String getUsername() {
        return context.getUsername();
    }


    @Override
    public void run() {
        isServing = true;
        while (isServing) {
            try {
                message = (ClientMessage) receiver.receive();
                if (message != null) {
                    logger.info("Received message");
                    if (context == null) {
                        logger.warn("Null context!");
                    }
                    message.exec(context);
                }
            } catch (ClassNotFoundException e) {
                logger.warn(e.getMessage());
            }
        }
    }
}
