package ru.nsu.ccfit.bogolepov;

import java.util.List;
import java.util.Map;

/**
 * Interface that will be used by ru.nsu.ccfit.bogolepov.WordCounter to print results
 */
public interface Generator {
    public void generate(List<Map.Entry<String, Integer>> words, int wordsCount);
}
