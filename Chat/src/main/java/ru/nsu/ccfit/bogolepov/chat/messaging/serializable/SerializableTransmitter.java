package ru.nsu.ccfit.bogolepov.chat.messaging.serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.Transmitter;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializableTransmitter implements Transmitter {
    private ObjectOutputStream stream;

    private Logger logger = LogManager.getLogger(getClass());

    public SerializableTransmitter(ObjectOutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void send(Message message) {
        try {
            stream.writeObject(message);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
