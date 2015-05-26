package ru.nsu.ccfit.bogolepov.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableReceiver;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableTransmitter;

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

    private int id;
    private static int handlerCounter = 0;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private boolean isServing;

    public RequestHandler(Socket socket, Server server) {
        id = ++handlerCounter;
        context = new RequestHandlerContext(server, transmitter, id);
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            transmitter = new SerializableTransmitter(outputStream);
            receiver = new SerializableReceiver(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Void sendMessage(Message message) {
        logger.trace(getClass().getSimpleName() + "::sendMessage");
        transmitter.send(message);
        return null;
    }

    public String getUsername() {
        return context.getUsername();
    }
    public int getUserId() {
        return id;
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
            } catch (IOException e) {
                logger.error(e.getMessage());
                break;
            }
        }
        close();
    }

    public void close() {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
