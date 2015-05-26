package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

import java.util.List;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class UsersListMessage implements ServerMessage {

    private List<String> users;

    public UsersListMessage(List<String> users) {
        this.users = users;
    }

    @Override
    public void exec(ClientContext context) {
        context.updateUsersList(users);
    }
}
