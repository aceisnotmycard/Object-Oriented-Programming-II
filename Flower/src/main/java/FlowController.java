import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Gets source of characters and reads it with Parser
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
        List<String> array = mParser.parse(mReader);
        mStorage.addWords(array);

    }
}
