package ru.nsu.ccfit.bogolepov.chat.messaging;

import java.io.IOException;
import java.util.Optional;

public interface Receiver<T extends Message> {
    Optional<T> receive() throws ClassNotFoundException, IOException;
    void close();
}
