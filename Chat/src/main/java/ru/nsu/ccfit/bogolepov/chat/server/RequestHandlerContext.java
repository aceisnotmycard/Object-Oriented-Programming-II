package ru.nsu.ccfit.bogolepov.chat.server;

import ru.nsu.ccfit.bogolepov.chat.messaging.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RequestHandlerContext implements ServerContext {
    private Server server;
    private String username;

    private Transmitter<Message<ClientContext>> transmitter;

    public RequestHandlerContext(Server server, Transmitter<Message<ClientContext>> transmitter, String name) {
        this.transmitter = transmitter;
        this.server = server;
        this.username = name;
    }

    @Override
    public void login(String username) {
        System.out.println("New user logged in: " + username);
        if (server.getUsernames().stream().filter(s -> s.equals(username)).count() > 0) {
            transmitter.send(ctx -> {
                ctx.showError("Username already taken");
                ctx.kick();
            });
        } else {
            this.username = username;
            server.broadcast(ctx -> ctx.notifyUserConnected(username));
        }
    }

    @Override
    public void logout() {
        System.out.println("Old friend " + username + " logged out");
        server.remove(username);
    }

    @Override
    public void send(String message, String to) {
        if (server.getUsernames().stream().filter(s -> s.equals(to)).count() > 0) {
            server.sendTo(message, to, getUsername());
        } else {
            transmitter.send(ctx -> ctx.showError("User " + to + " not found"));
        }
    }

    @Override
    public void getUsers() {
        System.out.println(username + " trying to get users list");
        // we need to remove reference to server
        // otherwise NotSerializable exception will be thrown
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
