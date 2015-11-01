package ru.nsu.ccfit.bogolepov.chat.messaging;


import java.io.Serializable;

public interface Message<T> extends Serializable {
    void exec(T context);
}
