package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

import java.io.Serializable;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class ServerTextMessage implements ServerMessage, Serializable{

    private String message;

    public ServerTextMessage(String text) {
        this.message = text;
    }

    @Override
    public void exec(ClientContext context) {
        context.showMessage(message);
    }
}
