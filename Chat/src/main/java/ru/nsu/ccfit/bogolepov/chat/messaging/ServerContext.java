package ru.nsu.ccfit.bogolepov.chat.messaging;

public interface ServerContext {
    void broadcast(Message message);
    void broadcast(String message);
    void login(String username);
    void logout();
    void getUsers();
}
