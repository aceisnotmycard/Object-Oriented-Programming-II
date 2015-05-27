package ru.nsu.ccfit.bogolepov.chat.messaging.server_messages;

import ru.nsu.ccfit.bogolepov.chat.messaging.ClientContext;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerMessage;

import java.io.Serializable;
import java.util.List;

public class UsersListMessage implements ServerMessage, Serializable {

    private List<String> users;

    public UsersListMessage(List<String> users) {
        this.users = users;
    }

    @Override
    public void exec(ClientContext context) {
        context.updateUsersList(users);
    }
}
