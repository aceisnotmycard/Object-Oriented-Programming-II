package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class ErrorMessage implements ServerMessage {

    private String error;

    public ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public void exec(ClientContext context) {
        context.showError(error);
    }
}
