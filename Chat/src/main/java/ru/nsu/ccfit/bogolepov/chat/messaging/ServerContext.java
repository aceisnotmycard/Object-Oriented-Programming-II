package ru.nsu.ccfit.bogolepov.chat.messaging;

public interface ServerContext {
    void broadcast(String message);
    void send(String message, String user);
    void login(String username);
    void logout();
    void getUsers();
}
