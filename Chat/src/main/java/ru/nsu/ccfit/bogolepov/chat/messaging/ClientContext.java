package ru.nsu.ccfit.bogolepov.chat.messaging;

import java.util.List;


public interface ClientContext {
    void showError(String message);
    void showMessage(String message);
    void showMessageFrom(String message, String from);
    void notifyUserConnected(String user);
    void notifyUserDisconnected(String user);
    void updateUsersList(List<String> users);
    void kick();
}
