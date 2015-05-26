package ru.nsu.ccfit.bogolepov.chat.messaging;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public interface ServerMessage extends Message {
    void exec(ClientContext context);
}
