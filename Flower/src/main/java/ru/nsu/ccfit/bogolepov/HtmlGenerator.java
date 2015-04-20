package ru.nsu.ccfit.bogolepov;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 * Created by aceisnotmycard on 3/11/15.
 */
public class HtmlGenerator implements Generator {

    Writer mWriter;

    HtmlGenerator(Writer writer) {
        mWriter = writer;
    }

    @Override
    public void generate(List<Map.Entry<String, Integer>> words, int wordsCount) {
        final int FLOAT_TO_PERCENT = 100;

        try {
            mWriter.append("<!DOCTYPE html>");
            mWriter.append("<html>");
            mWriter.append("<body>");
            mWriter.append("<table>");
            for (Map.Entry word : words) {
                mWriter.append("<tr>");
                mWriter.append("<td>" + word.getKey().toString() + "</td>");
                mWriter.append("<td>" + word.getValue().toString()  + "</td>");
                mWriter.append("<td>" + ((Integer) (FLOAT_TO_PERCENT * (Integer) word.getValue() / wordsCount)).toString()  + "</td>");
                mWriter.append("</tr>");
            }
            mWriter.append("</table>");
            mWriter.append("</body>");
            mWriter.append("</html>");
            mWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + e.getLocalizedMessage());
        }
    }
}
