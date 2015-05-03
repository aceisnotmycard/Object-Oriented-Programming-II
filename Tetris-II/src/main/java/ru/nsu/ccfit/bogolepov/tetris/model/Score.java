package ru.nsu.ccfit.bogolepov.tetris.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Score extends Observable {

    private static final String RECORDS_FILE = "records.txt";

    Integer score;

    public Score() {
        score = 0;
    }

    /** add number of deleted rows */
    public void add(int count) {
        score += count*count;
        setChanged();
        notifyObservers(score);
    }

    public Integer get() {
        return score;
    }

    /** writes score to records file if it's really a new record */
    public void saveIfBest() {
        int positionToReplace = -1;
        List<Integer> recordsList = getBestResults();
        for (int i = 0; i < recordsList.size(); i++) {
            if (score > recordsList.get(i)) {
                positionToReplace = i;
            }
        }
        if (positionToReplace > -1) {
            for (int i = positionToReplace; i < recordsList.size() - 1; i++) {
                recordsList.set(i + 1, recordsList.get(i));
            }
            recordsList.set(positionToReplace, score);
        }
        PrintWriter writer;
        try {
            writer = new PrintWriter(
                    new File(this.getClass().getResource(RECORDS_FILE).getPath()));
        } catch (FileNotFoundException e) {
            writer = null;
        }
        if (writer != null) {
            for (Integer record : recordsList) {
                writer.write(record + "\n");
            }
        }

    }

    public List<Integer> getBestResults() {
        ArrayList<Integer> recordsList = new ArrayList<>();
        InputStream stream = getClass().getClassLoader().getResourceAsStream(RECORDS_FILE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    recordsList.add(new Integer(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        recordsList.sort(Integer::compareTo);
        return recordsList;
    }
}
