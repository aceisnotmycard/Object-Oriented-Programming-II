package ru.nsu.ccfit.bogolepov.chat.server;

import ru.nsu.ccfit.bogolepov.chat.messaging.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestHandlerContext implements ServerContext {
    private Server server;
    private String username;

    private Transmitter<Message<ClientContext>> transmitter;

    public RequestHandlerContext(Server server, Transmitter<Message<ClientContext>> transmitter) {
        this.transmitter = transmitter;
        this.server = server;
    }

    @Override
    public void login(String username) {
        System.out.println("New user logged in: " + username);
        this.username = username;
        server.broadcast(ctx -> ctx.notifyUserConnected(username));
    }

    @Override
    public void logout() {
        System.out.println("Old friend " + username + " logged out");
        server.remove(username);
        server.broadcast(ctx -> ctx.notifyUserDisconnected(username));
    }

    @Override
    public void send(String message, String user) {
    }

    @Override
    public void getUsers() {
        System.out.println(username + " trying to get users list");
        List<String> a = server.getUsernames();
        List<String> b = new ArrayList<>(a);
        Collections.copy(b, a);
        transmitter.send(ctx -> ctx.updateUsersList(b));
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void broadcast(String message) {
        System.out.println(username + " broadcasting: " + message);
        server.broadcast(ctx -> ctx.showMessage(message));
    }
}
