package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;


public class UserDisconnectedMessage implements ServerMessage {

    private String user;

    public UserDisconnectedMessage(String user) {
        this.user = user;
    }

    @Override
    public void exec(ClientContext context) {
        context.notifyUserDisconnected(user);
    }
}
