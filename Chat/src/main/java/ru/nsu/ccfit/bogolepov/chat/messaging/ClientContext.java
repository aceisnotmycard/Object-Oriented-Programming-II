package ru.nsu.ccfit.bogolepov.chat.messaging;

import java.util.List;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public interface ClientContext {
    void showError(String message);
    void showMessage(String message);
    void notifyUserConnected(String user);
    void notifyUserDisconnected(String user);
    void updateUsersList(List<String> users);
}
