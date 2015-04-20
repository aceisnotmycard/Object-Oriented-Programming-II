import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * Created by aceisnotmycard on 3/11/15.
 */
public class CsvGenerator implements Generator {

    Writer writer;

    CsvGenerator(Writer writer) {
        this.writer = writer;
    }

    @Override
    public void generate(List<Map.Entry<String, Integer>> words, int wordsCount) {
        final String FORMAT = "WORD,COUNT,PERCENT\n";
        final int FLOAT_TO_PERCENT = 100;

        try {
            writer.append(FORMAT);

            for (Map.Entry word : words) {
                writer.append(word.getKey().toString());
                writer.append(",");
                writer.append(word.getValue().toString());
                writer.append(",");
                writer.append(((Integer) (FLOAT_TO_PERCENT * (Integer) word.getValue() / wordsCount)).toString());
                writer.append('\n');
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + e.getLocalizedMessage());
        }
    }
}
