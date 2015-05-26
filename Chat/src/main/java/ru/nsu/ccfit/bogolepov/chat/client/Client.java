package ru.nsu.ccfit.bogolepov.chat.client;

import ru.nsu.ccfit.bogolepov.chat.messaging.*;
import ru.nsu.ccfit.bogolepov.chat.messaging.client_messages.GetUsersMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.client_messages.LogoutMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.client_messages.TextMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableReceiver;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableTransmitter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    private Socket socket;
    private String username;

    private Receiver receiver;
    private Transmitter transmitter;
    private ClientContext context;
    private ClientMessage message;
    private ClientView view;

    public Client(String server, int port) {
        try {
            socket = new Socket(server, port);
            transmitter = new SerializableTransmitter(new ObjectOutputStream(socket.getOutputStream()));
            receiver = new SerializableReceiver(new ObjectInputStream(socket.getInputStream()));
            context = new ServerListenerContext(this);
            view = new ClientView(this);
        } catch (UnknownHostException e) {

        } catch (IOException e) {

        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void start() {
        Thread thread = new Thread(new ServerListener(receiver, context));
        thread.start();
    }

    public void sendMessage(String text) {
        message = new TextMessage(text);
        transmitter.send(message);
    }

    public void getUsers() {
        transmitter.send(new GetUsersMessage());
    }

    public void disconnect() {
        transmitter.send(new LogoutMessage());
        try {
            socket.close();
        } catch (IOException e) {

        }
    }

    public void showMessage(String text) {
        view.appendMessage(text);
    }
}
