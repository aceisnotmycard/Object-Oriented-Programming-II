package ru.nsu.ccfit.bogolepov.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.Transmitter;
import ru.nsu.ccfit.bogolepov.chat.messaging.server_messages.*;

import java.util.List;


public class RequestHandlerContext implements ServerContext {
    private Server server;
    private String username;

    private int id;

    private Logger logger = LogManager.getLogger(getClass());
    private Transmitter transmitter;

    public RequestHandlerContext(Server server, Transmitter transmitter, int id) {
        this.id = id;
        this.transmitter = transmitter;
        this.server = server;
    }

    @Override
    public void broadcast(Message message) {

        logger.trace(getClass().getSimpleName() + "::broadcast");
        if (!server.broadcast(message)) {
            transmitter.send(new ErrorMessage("Cannot broadcast message to other users"));
        }
    }

    @Override
    public void login(String username) {
        this.username = username;
        if (!server.broadcast(new UserConnectedMessage(username))) {
            transmitter.send(new ErrorMessage("Cannot login"));
        }
    }

    @Override
    public void logout() {
        server.remove(id);
        if (!server.broadcast(new UserDisconnectedMessage(username))) {
            transmitter.send(new ErrorMessage("Cannot disconnect, lol"));
        }
    }

    @Override
    public void getUsers() {
        logger.trace(getClass().getSimpleName() + "::getUsers");
        List<String> usernames = server.getUsernames();
        if (usernames != null) {
            transmitter.send(new UsersListMessage(usernames));
        } else {
            transmitter.send(new ErrorMessage("Cannot retrieve list of users"));
        }
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void broadcast(String message) {
        broadcast(new ServerTextMessage(username + ": " + message));
    }
}
