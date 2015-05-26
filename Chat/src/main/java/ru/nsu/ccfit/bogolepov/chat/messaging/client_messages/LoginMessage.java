package ru.nsu.ccfit.bogolepov.chat.messaging.client_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class LoginMessage implements ClientMessage{

    private String username;

    public LoginMessage(String username) {
        this.username = username;
    }

    @Override
    public void exec(ServerContext context) {
        context.login(username);
    }
}
