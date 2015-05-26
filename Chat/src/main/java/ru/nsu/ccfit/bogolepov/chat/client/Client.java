package ru.nsu.ccfit.bogolepov.chat.client;

import ru.nsu.ccfit.bogolepov.chat.messaging.*;
import ru.nsu.ccfit.bogolepov.chat.messaging.client_messages.GetUsersMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.client_messages.LoginMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.client_messages.LogoutMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.client_messages.TextMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableReceiver;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableTransmitter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;


public class Client {

    private Socket socket;
    private String username = "Anon";

    private Receiver receiver;
    private Transmitter transmitter;
    private ClientContext context;
    private ClientView view;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Client(String server, int port) {
        try {
            socket = new Socket(server, port);
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            transmitter = new SerializableTransmitter(outputStream);
            receiver = new SerializableReceiver(inputStream);
            context = new ServerListenerContext(this);
            view = new ClientView(this);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void start() {
        Thread thread = new Thread(new ServerListener(receiver, context));
        thread.start();
        transmitter.send(new LoginMessage(username));
    }

    public void sendMessage(String text) {
        if (!parse(text)) {
            return;
        }
        transmitter.send(new TextMessage(text));
    }

    public void getUsers() {
        transmitter.send(new GetUsersMessage());
    }

    public void disconnect() {
        transmitter.send(new LogoutMessage());
        try {
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        view.appendMessage(text);
    }

    public void showUsers(List<String> users) {
        showMessage("USERS ONLINE");
        users.forEach(this::showMessage);
    }

    private boolean parse(String text) {
        return text.length() > 0;
    }
}