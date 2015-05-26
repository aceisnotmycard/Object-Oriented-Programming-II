package ru.nsu.ccfit.bogolepov.chat.messaging;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public interface ServerContext {
    void broadcast(String message);
    void login(String username);
    void logout();
    void getUsers();
}
