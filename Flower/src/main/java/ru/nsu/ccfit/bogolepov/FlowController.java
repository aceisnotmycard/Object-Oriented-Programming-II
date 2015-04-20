package ru.nsu.ccfit.bogolepov;

import java.io.Reader;
import java.util.List;

/**
 * Gets source of characters and reads it with ru.nsu.ccfit.bogolepov.Parser
 * Stores words in storage
 */
public class FlowController {

    Reader mReader;
    Storage mStorage;
    Parser mParser;

    FlowController(Reader reader, Storage storage, Parser parser) {

        mStorage = storage;
        mParser = parser;
        mReader = reader;
    }

    void parse() {
        String word;
        while ((word = mParser.parse(mReader)) != null ) {
            mStorage.addWord(word);
        }
    }
}
