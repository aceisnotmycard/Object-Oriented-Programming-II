package ru.nsu.ccfit.bogolepov.chat.messaging.client_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;

import java.io.Serializable;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class LoginMessage implements ClientMessage, Serializable {

    private String username;

    public LoginMessage(String username) {
        this.username = username;
    }

    @Override
    public void exec(ServerContext context) {
        context.login(username);
    }
}
