package ru.nsu.ccfit.bogolepov.chat.messaging.serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.Receiver;

import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializableReceiver implements Receiver {
    Logger logger = LogManager.getLogger(getClass());
    ObjectInputStream inputStream;

    public SerializableReceiver(ObjectInputStream stream) {
        this.inputStream = stream;
    }

    public Message receive() throws ClassNotFoundException, IOException {
        Message message;
        message = (Message) inputStream.readObject();
        if (message != null) {
            logger.info("Message read successfully");
        }
        return message;
    }
}
