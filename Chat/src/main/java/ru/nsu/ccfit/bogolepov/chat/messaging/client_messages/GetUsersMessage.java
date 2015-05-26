package ru.nsu.ccfit.bogolepov.chat.messaging.client_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;

import java.io.Serializable;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class GetUsersMessage implements ClientMessage, Serializable {
    @Override
    public void exec(ServerContext context) {
        context.getUsers();
    }
}
