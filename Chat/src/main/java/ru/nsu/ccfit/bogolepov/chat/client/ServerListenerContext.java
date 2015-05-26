package ru.nsu.ccfit.bogolepov.chat.client;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;

import java.util.List;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class ServerListenerContext implements ClientContext {

    Client client;

    public ServerListenerContext(Client client) {
        this.client = client;
    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void showMessage(String message) {
        client.showMessage(message);
    }

    @Override
    public void notifyUserConnected(String user) {

    }

    @Override
    public void notifyUserDisconnected(String user) {

    }

    @Override
    public void updateUsersList(List<String> users) {

    }
}
