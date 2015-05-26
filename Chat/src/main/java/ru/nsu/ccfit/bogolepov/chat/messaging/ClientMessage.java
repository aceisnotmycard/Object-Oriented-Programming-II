package ru.nsu.ccfit.bogolepov.chat.messaging;

public interface ClientMessage extends Message {
    void exec(ServerContext context);
}
