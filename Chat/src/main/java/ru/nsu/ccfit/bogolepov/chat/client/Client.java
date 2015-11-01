package ru.nsu.ccfit.bogolepov.chat.client;

import ru.nsu.ccfit.bogolepov.chat.messaging.*;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableReceiver;
import ru.nsu.ccfit.bogolepov.chat.messaging.serializable.SerializableTransmitter;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;


public class Client {

    private Socket socket;

    private Receiver<Message<ClientContext>> receiver;
    private Transmitter<Message<ServerContext>> transmitter;
    private ClientContext context;
    private String username;

    public Client() {
    }

    public void start(String host, int port, String username) {
        try {
            this.username = username;
            socket = new Socket(host, port);
            transmitter = new SerializableTransmitter<>(new ObjectOutputStream(socket.getOutputStream()));
            receiver = new SerializableReceiver<>(new ObjectInputStream(socket.getInputStream()));
            context = new ServerListenerContext(this);
            Thread thread = new Thread(new ServerListener(receiver, context));
            thread.start();
            Runnable r = () -> parseCommand(new InputStreamReader(System.in));
            Thread ui = new Thread(r);
            ui.start();
            login();
        } catch (UnknownHostException e) {
            disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendMessage(String text, String toUser) {
        if (!parse(text)) {
            return;
        }
        transmitter.send(ctx -> ctx.broadcast(text));
    }

    public synchronized void getUsers() {
        transmitter.send(ServerContext::getUsers);
    }

    public synchronized void disconnect() {
        System.out.println("Disconnecting");
        transmitter.send(ServerContext::logout);
        transmitter.close();
        receiver.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void login() {
        String u = String.copyValueOf(username.toCharArray());
        transmitter.send(ctx -> ctx.login(u));
    }

    public synchronized void show(String msg) {
        System.out.println(msg);
    }

    public synchronized void showUsers(List<String> users) {
        show("USERS ONLINE");
        users.forEach(this::show);
    }

    private boolean parse(String text) {
        return text.length() > 0;
    }

    private void parseCommand(InputStreamReader stream) {
        BufferedReader bufferedReader = new BufferedReader(stream);
        try {
            String input = bufferedReader.readLine();
            switch (input) {
                case "GETUSERS":
                    getUsers();
                    break;
                case "DISCONNECT":
                    disconnect();
                    break;
                case "SEND":
                    System.out.print("TO: ");
                    String user = bufferedReader.readLine();
                    System.out.print("MESSAGE: ");
                    String message = bufferedReader.readLine();
                    sendMessage(message, user);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
