package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

import java.io.Serializable;

public class UserConnectedMessage implements ServerMessage, Serializable {

    private String user;

    public UserConnectedMessage(String user) {
        this.user = user;
    }

    @Override
    public void exec(ClientContext context) {
        context.notifyUserConnected(user);
    }
}
