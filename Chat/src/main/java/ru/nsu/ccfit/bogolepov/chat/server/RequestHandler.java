package ru.nsu.ccfit.bogolepov.chat.server;

import ru.nsu.ccfit.bogolepov.chat.messaging.*;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableReceiver;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableTransmitter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler extends Thread {

    private RequestHandlerContext context;
    private Transmitter<Message<ClientContext>> transmitter;
    private Receiver<Message<ServerContext>> receiver;

    private boolean isServing;

    public RequestHandler(Socket socket, Server server) {
        try {
            transmitter = new SerializableTransmitter<>(new ObjectOutputStream(socket.getOutputStream()));
            receiver = new SerializableReceiver<>(new ObjectInputStream(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        context = new RequestHandlerContext(server, transmitter);
    }

    public void sendMessage(Message<ClientContext> message) {
        transmitter.send(message);
    }

    public String getUsername() {
        return context.getUsername();
    }

    public boolean isRequiredUser(String required) {
        return getUsername().equals(required);
    }

    @Override
    public void run() {
        isServing = true;
        while (isServing) {
            try {
                receiver.receive().ifPresent(msg -> {
                    System.out.println("Message received from " + getUsername());
                    msg.exec(context);
                });
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        close();
    }

    public void close() {
        isServing = false;
        transmitter.close();
        receiver.close();
    }
}
