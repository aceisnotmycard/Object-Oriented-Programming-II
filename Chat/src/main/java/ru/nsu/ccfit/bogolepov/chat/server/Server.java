package ru.nsu.ccfit.bogolepov.chat.server;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Server {

    private List<RequestHandler> handlers;
    private int port;

    public Server(int port) {
        handlers = new ArrayList<>();
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                RequestHandler rh = new RequestHandler(socket, this);
                handlers.add(rh);
                rh.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        handlers.forEach(rh -> rh.sendMessage(ctx -> {
            ctx.showError("Server is shutting down");
            ctx.kick();
        }));
    }

    public void sendTo(String message, String to, String from) {
        handlers.stream().filter(rh -> rh.isRequiredUser(to)).findFirst()
                .ifPresent(rh -> rh.sendMessage(ctx -> ctx.showMessageFrom(message, from)));
    }

    synchronized public void broadcast(Message<ClientContext> message) {
        handlers.forEach(rh -> rh.sendMessage(message));
    }

    synchronized public List<String> getUsernames() {
        return handlers.stream().map(RequestHandler::getUsername)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    synchronized public void remove(String username) {
        handlers.stream().filter(rh -> rh.isRequiredUser(username))
                .findFirst().ifPresent(RequestHandler::close);
        handlers.removeIf(rh -> rh.isRequiredUser(username));
        broadcast(ctx -> ctx.notifyUserDisconnected(username));
    }
}
