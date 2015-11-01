package ru.nsu.ccfit.bogolepov.chat.messaging;

/**
 * Created by aceisnotmycard on 5/26/15.
 */
public interface Transmitter<T extends Message> {
    void send(T message);

    void close();
}
