import java.util.List;
import java.util.Map;

/**
 * Created by aceisnotmycard on 3/4/15.
 */
public interface Storage {

    public void addWords(List<String> word);
    public void addWord(String word);
    public Map<String, Integer> getWords();
}
