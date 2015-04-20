import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by aceisnotmycard on 3/4/15.
 */
public class HashStorage implements Storage {

    private static final HashStorage instance = new HashStorage();

    private static int DEFAULT_COUNT = 1;
    private static int INCREMENT = 1;

    private HashMap<String, Integer> storage = new HashMap<>();

    public static HashStorage getInstance() {
        return instance;
    }

    @Override
    public void addWords(List<String> words) {
        for (String word : words) {
            addWord(word);
        }
    }

    @Override
    public void addWord(String word) {
        if (storage.containsKey(word)) {
            for (Entry<String, Integer> wordEntry : storage.entrySet()) {
                if (wordEntry.getKey().equals(word)) {
                    wordEntry.setValue(wordEntry.getValue() + INCREMENT);
                    break;
                }
            }
        } else {
            storage.put(word, DEFAULT_COUNT);
        }
    }

    @Override
    public Map<String, Integer> getWords() {
        return storage;
    }
}
