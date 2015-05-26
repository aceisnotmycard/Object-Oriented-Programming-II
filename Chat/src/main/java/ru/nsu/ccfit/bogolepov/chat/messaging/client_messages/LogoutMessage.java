package ru.nsu.ccfit.bogolepov.chat.messaging.client_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;

import java.io.Serializable;


public class LogoutMessage implements ClientMessage, Serializable {
    @Override
    public void exec(ServerContext context) {
        context.logout();
    }
}
