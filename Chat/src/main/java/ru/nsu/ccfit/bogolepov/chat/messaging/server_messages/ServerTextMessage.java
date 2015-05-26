package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class ServerTextMessage implements ServerMessage {

    private String message;

    public ServerTextMessage(String text) {
        this.message = text;
    }

    @Override
    public void exec(ClientContext context) {
        context.showMessage(message);
    }
}
