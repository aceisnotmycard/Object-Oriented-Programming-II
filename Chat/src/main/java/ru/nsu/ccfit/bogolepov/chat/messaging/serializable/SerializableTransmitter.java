package ru.nsu.ccfit.bogolepov.chat.messaging.serializable;

import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.Transmitter;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public class SerializableTransmitter implements Transmitter {
    private ObjectOutputStream stream;

    public SerializableTransmitter(ObjectOutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void send(Message message) {
        try {
            stream.writeObject(message);
        } catch (IOException e) {
            // TODO: process exception
        }
    }
}
