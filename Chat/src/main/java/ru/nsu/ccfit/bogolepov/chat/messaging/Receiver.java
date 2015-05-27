package ru.nsu.ccfit.bogolepov.chat.messaging;

import java.io.IOException;

public interface Receiver {
    Message receive() throws ClassNotFoundException, IOException;
}
