package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class UserConnectedMessage implements ServerMessage {

    private String user;

    public UserConnectedMessage(String user) {
        this.user = user;
    }

    @Override
    public void exec(ClientContext context) {
        context.notifyUserConnected(user);
    }
}
