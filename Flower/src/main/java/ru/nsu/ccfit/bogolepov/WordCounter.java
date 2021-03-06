package ru.nsu.ccfit.bogolepov;

import java.util.*;
import java.util.Map.Entry;

/**
 * Processes information from ru.nsu.ccfit.bogolepov.Storage
 * Holds ru.nsu.ccfit.bogolepov.Generator to generate processed information
 */
public class WordCounter {

    Storage mStorage;
    Generator mGenerator;

    private Map<String, Integer> content;

    WordCounter(Storage storage, Generator generator) {
        mStorage = storage;
        mGenerator = generator;
        content = storage.getWords();
    }

    public List<Entry<String, Integer>> generateSortedList() {
        List<Entry<String, Integer>> words = new ArrayList<>(content.entrySet());
        Collections.sort(words, (a, b) -> Integer.compare(b.getValue(), a.getValue()));
        return words;
    }

    public int countWords() {
        int sum = 0;
        for (Entry word : content.entrySet()) {
            sum += (int) word.getValue();
        }
        return sum;
    }

    public void generate() {
        mGenerator.generate(generateSortedList(), countWords());
    }
}
