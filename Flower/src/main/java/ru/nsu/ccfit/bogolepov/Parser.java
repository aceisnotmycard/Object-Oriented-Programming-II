package ru.nsu.ccfit.bogolepov;

import java.io.Reader;
import java.util.List;

/**
 * Parsing input from ru.nsu.ccfit.bogolepov.FlowController
 */
public interface Parser {
    public String parse(Reader reader);
}
