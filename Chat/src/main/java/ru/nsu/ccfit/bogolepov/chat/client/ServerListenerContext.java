package ru.nsu.ccfit.bogolepov.chat.client;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;

import java.util.List;

public class ServerListenerContext implements ClientContext {

    Client client;

    public ServerListenerContext(Client client) {
        this.client = client;
    }

    @Override
    public void showError(String message) {
        client.showMessage("ERROR: " + message);
    }

    @Override
    public void showMessage(String message) {
        client.showMessage(message);
    }

    @Override
    public void notifyUserConnected(String user) {
        client.showMessage(user +  " connected");
    }

    @Override
    public void notifyUserDisconnected(String user) {
        client.showMessage(user + " disconnected");
    }

    @Override
    public void updateUsersList(List<String> users) {
        client.showUsers(users);
    }
}
