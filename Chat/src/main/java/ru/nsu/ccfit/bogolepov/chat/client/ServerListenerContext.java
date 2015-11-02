package ru.nsu.ccfit.bogolepov.chat.client;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;

import java.util.List;

public class ServerListenerContext implements ClientContext {

    private Client client;

    public ServerListenerContext(Client client) {
        this.client = client;
    }

    @Override
    public void showError(String message) {
        client.show("ERROR: " + message);
    }

    @Override
    public void showMessage(String message) {
        client.show(message);
    }

    @Override
    public void showMessageFrom(String message, String from) {
        client.show(from + ": " + message);
    }

    @Override
    public void kick() {
        System.exit(0);
    }

    @Override
    public void notifyUserConnected(String user) {
        client.show(user +  " connected");
    }

    @Override
    public void notifyUserDisconnected(String user) {
        client.show(user + " disconnected");
    }

    @Override
    public void updateUsersList(List<String> users) {
        client.showUsers(users);
    }
}
