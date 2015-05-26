package ru.nsu.ccfit.bogolepov.chat.messaging;

import java.io.IOException;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public interface Receiver {
    Message receive() throws ClassNotFoundException, IOException;
}
