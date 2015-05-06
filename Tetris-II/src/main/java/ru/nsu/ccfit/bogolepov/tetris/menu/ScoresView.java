package ru.nsu.ccfit.bogolepov.tetris.menu;

import ru.nsu.ccfit.bogolepov.tetris.model.Score;

import javax.swing.*;
import java.util.List;

public class ScoresView extends JFrame {

    List<Integer> scoreArrayList;
    private static final int NUMBER_OF_RECORDS = 5;

    ScoresView() {
        scoreArrayList = Score.getResults();

        JTextArea textArea = new JTextArea();
        int numberOfShowingRecords = NUMBER_OF_RECORDS < scoreArrayList.size() ? NUMBER_OF_RECORDS : scoreArrayList.size();
        for (Integer i = 1; i <= numberOfShowingRecords; i++) {
            textArea.append(i.toString() + ": " + scoreArrayList.get(scoreArrayList.size()-i) + "\n");
        }

        add(textArea);
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
