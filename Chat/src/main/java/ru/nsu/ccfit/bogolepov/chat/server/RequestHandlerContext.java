package ru.nsu.ccfit.bogolepov.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.Transmitter;
import ru.nsu.ccfit.bogolepov.chat.messaging.server_messages.UsersListMessage;

import java.util.List;


public class RequestHandlerContext implements ServerContext {
    private Server server;
    private String username;

    private Logger logger = LogManager.getLogger(getClass());
    private Transmitter transmitter;

    public RequestHandlerContext(Server server, Transmitter transmitter) {
        this.transmitter = transmitter;
        this.server = server;
    }

    @Override
    public void broadcast(String message) {

        logger.trace(getClass().getSimpleName() + "::broadcast");
        server.broadcast(username + ": " + message);
    }

    @Override
    public void login(String username) {
        this.username = username;
    }

    @Override
    public void logout() {

    }

    @Override
    public void getUsers() {
        logger.trace(getClass().getSimpleName() + "::getUsers");
        List<String> usernames = server.getUsernames();
        transmitter.send(new UsersListMessage(usernames));
    }

    public String getUsername() {
        return username;
    }
}
