package ru.nsu.ccfit.bogolepov.chat.messaging.serializable;

import ru.nsu.ccfit.bogolepov.chat.messaging.Message;
import ru.nsu.ccfit.bogolepov.chat.messaging.Receiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Optional;

public class SerializableReceiver<T extends Message> implements Receiver<T> {
    private ObjectInputStream inputStream;

    public SerializableReceiver(ObjectInputStream stream) {
        this.inputStream = stream;
    }

    public Optional<T> receive() throws ClassNotFoundException, IOException {
        return Optional.of((T) inputStream.readObject());
    }

    @Override
    public void close() {
        try {
            inputStream.close();
        } catch (IOException e) {
            System.out.println("Cannot close receiver.");
        }
    }
}
