package ru.nsu.ccfit.bogolepov.chat.messaging.client_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.Xmlable;

import java.io.Serializable;

public class LoginMessage implements ClientMessage, Serializable, Xmlable {

    private String username;

    public LoginMessage(String username) {
        this.username = username;
    }

    @Override
    public void exec(ServerContext context) {
        context.login(username);
    }
}
