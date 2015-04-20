package ru.nsu.ccfit.bogolepov;

import java.io.Reader;
import java.io.Writer;

/**
 * Wrapper for all logic
 */
public class Processor {

    FlowController flowController;
    WordCounter wordCounter;

    Processor(Reader reader, Writer writer, String format) {
        flowController = new FlowController(reader, HashStorage.getInstance(), new PlainParser());
        Generator generator;

        switch (format) {
            case "html":
                generator = new HtmlGenerator(writer);
                break;
            case "csv":
                generator = new CsvGenerator(writer);
                break;
            default:
                throw new IllegalArgumentException();
        }

        wordCounter = new WordCounter(HashStorage.getInstance(), generator);
    }

    void process() {
        flowController.parse();
        wordCounter.generate();
    }
}
