import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aceisnotmycard on 3/10/15.
 */
public class PlainParser implements Parser {
    @Override
    public List<String> parse(Reader reader) {
        List<String> words = new ArrayList<String>();
        StringBuilder wordBuilder = new StringBuilder();
        int symbol;
        try {
            while ((symbol = reader.read()) != -1) {
                char character = (char) symbol;
                if (Character.isLetterOrDigit(character)) {
                    wordBuilder.append(character);
                } else {
                    if (wordBuilder.length() > 0) {
                        words.add(wordBuilder.toString());
                        wordBuilder.setLength(0);
                    }
                }
            }
            // Inserting last word
            if (wordBuilder.length() > 0) {
                words.add(wordBuilder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
