package ru.nsu.ccfit.bogolepov.chat.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Server {

    private Logger logger = LogManager.getLogger(getClass());

    private List<RequestHandler> requestHandlerList;
    private int port;

    public Server(int port) {
        requestHandlerList = new ArrayList<>();
        this.port = port;
    }

    public void start() {
        logger.info("Server started...");
        boolean isServing = true;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(isServing) {
                Socket socket = serverSocket.accept();
                RequestHandler rh = new RequestHandler(socket, this);
                requestHandlerList.add(rh);
                rh.start();
            }
        } catch (IOException e) {
        }
    }

    public boolean broadcast(Message message) {
        logger.trace("Server::broadcast");
        requestHandlerList.forEach(rh -> rh.sendMessage(message));
        return true;
    }

    public List<String> getUsernames() {
        logger.trace("Server::getUsernames");
        List<String> usernames = new ArrayList<>();
        requestHandlerList.forEach(rh -> usernames.add(rh.getUsername()));
        return usernames;
    }
}
