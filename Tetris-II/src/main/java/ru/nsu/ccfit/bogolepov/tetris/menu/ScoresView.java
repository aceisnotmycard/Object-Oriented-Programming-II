package ru.nsu.ccfit.bogolepov.tetris.menu;

import ru.nsu.ccfit.bogolepov.tetris.model.Score;

import javax.swing.*;
import java.util.List;

/**
 * Created by aceisnotmycard on 5/5/15.
 */
public class ScoresView extends JFrame {

    List<Integer> scoreArrayList;
    private static final int NUMBER_OF_RECORDS = 5;

    ScoresView() {
        Score score = new Score();
        scoreArrayList = score.getBestResults();

        JTextArea textArea = new JTextArea();
        int numberOfShowingRecords = NUMBER_OF_RECORDS < scoreArrayList.size() ? NUMBER_OF_RECORDS : scoreArrayList.size();
        for (Integer i = 0; i < numberOfShowingRecords; i++) {
            textArea.append(i.toString() + ": " + scoreArrayList.get(i) + "\n");
        }

        add(textArea);
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
