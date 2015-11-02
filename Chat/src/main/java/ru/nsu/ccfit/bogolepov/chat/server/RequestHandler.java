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
            System.out.println("Cannot create Request handler: " + e.getMessage());
        }
        context = new RequestHandlerContext(server, transmitter,
                socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
    }

    public void sendMessage(Message<ClientContext> message) {
        transmitter.send(message);
    }

    public String getUsername() {
        return context.getUsername();
    }

    public boolean isRequiredUser(String requiredUser) {
        return getUsername() != null && getUsername().equals(requiredUser);
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
                System.out.println("Received message is incorrect");
            } catch (IOException e) {
                System.out.println("Something went wrong while receiving message from " + getUsername());
            }
        }
        transmitter.close();
        receiver.close();
    }

    public void close() {
        isServing = false;
    }
}
