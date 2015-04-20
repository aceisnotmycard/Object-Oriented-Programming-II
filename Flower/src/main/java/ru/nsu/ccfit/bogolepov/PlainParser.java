package ru.nsu.ccfit.bogolepov;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aceisnotmycard on 3/10/15.
 */
public class PlainParser implements Parser {

    private StringBuilder wordBuilder = new StringBuilder();

    @Override
    public String parse(Reader reader) {
        int symbol;
        wordBuilder.setLength(0);
        try {
            while ((symbol = reader.read()) != -1) {
                char character = (char) symbol;
                if (Character.isLetterOrDigit(character)) {
                    wordBuilder.append(character);
                } else {
                    if (wordBuilder.length() > 0) {
                        return wordBuilder.toString();
                    }
                }
            }
            // Inserting last word
            if (wordBuilder.length() > 0) {
                return wordBuilder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
