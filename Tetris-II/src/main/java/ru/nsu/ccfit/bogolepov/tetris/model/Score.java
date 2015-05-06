package ru.nsu.ccfit.bogolepov.tetris.model;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Score extends Observable {

    private static final String RECORDS_FILE = "records.txt";

    static {
        File recordsFile = new File(RECORDS_FILE);
        if (!recordsFile.exists()) {
            try {
                recordsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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
    public static void save(Integer score) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(RECORDS_FILE, true)))) {
            out.println(score);
        }catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static List<Integer> getResults() {
        ArrayList<Integer> recordsList = new ArrayList<>();
        String line;
        try {
            InputStream stream = new FileInputStream(RECORDS_FILE);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            while ((line = reader.readLine()) != null) {
                if (line.length() > 0) {
                    recordsList.add(new Integer(line));
                }
            }
            try {
                reader.close();
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        recordsList.sort(Integer::compareTo);
        return recordsList;
    }
}
