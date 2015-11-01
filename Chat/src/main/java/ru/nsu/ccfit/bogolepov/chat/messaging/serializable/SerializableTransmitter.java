package ru.nsu.ccfit.bogolepov.chat.messaging.serializable;

import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.Transmitter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTransmitter<T extends Message> implements Transmitter<T> {
    private ObjectOutputStream stream;

    public SerializableTransmitter(ObjectOutputStream stream) {
        this.stream = stream;
    }

    @Override
    public void send(T message) {
        try {
            stream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
