package ru.nsu.ccfit.bogolepov.chat.messaging.client_messages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.ClientMessage;
import ru.nsu.ccfit.bogolepov.chat.messaging.ServerContext;

import java.io.Serializable;

public class TextMessage implements ClientMessage, Serializable {

    transient Logger logger = LogManager.getLogger(getClass());

    private String text;

    public TextMessage(String text) {
        this.text = text;
    }

    @Override
    public void exec(ServerContext context) {
        context.broadcast(text);
        //logger.info("broadcasting message...");
    }
}
