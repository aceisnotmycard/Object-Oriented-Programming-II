package ru.nsu.ccfit.bogolepov;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aceisnotmycard on 3/11/15.
 */
public class Main {

    private static final int DEFAULT_ARGS_LENGTH = 3;

    private static final String[] FORMATS = {
            "html",
            "csv"
    };

    public static void main(String[] args) {
        if (parseArguments(args)) {

            File inputFile = new File(args[0]);
            File outputFile = new File(args[1]);

            if (!inputFile.exists()) {
                System.err.println("File " + inputFile.getName() + " not found!");
                return;
            }

            if (!inputFile.canRead()) {
                System.err.println("File " + inputFile.getName() + " can't be read!");
                return;
            }


            try (Reader reader = new FileReader(inputFile.getAbsolutePath());
                 Writer writer = new FileWriter(outputFile.getAbsoluteFile())) {
                try {
                    Processor processor = new Processor(reader, writer, args[2]);
                    processor.process();
                } catch (IllegalArgumentException e) {
                    System.out.println("Wrong argument " + e.getLocalizedMessage());
                }
            } catch (IOException e) {
                System.err.println("I/O Exception: " + e.getLocalizedMessage());
            }
        }
    }

    static boolean parseArguments(String[] args) {

        final String HELP = "Program arguments: \n" +
                "First argument: file with words \n" +
                "Second argument: name of file that will be created to store CSV table \n" +
                "Third argument: output file format";

        if (DEFAULT_ARGS_LENGTH != args.length) {
            System.err.println("Number of arguments should be " + DEFAULT_ARGS_LENGTH);
            System.err.println(HELP);
            printSupportedFormats();
            return false;
        }

        ArrayList<String> formatsList = new ArrayList<>(Arrays.asList(FORMATS));

        if (!formatsList.contains(args[2])) {
            System.err.println("Unsupported format!");
            printSupportedFormats();
            return false;
        }

        return true;
    }

    static void printSupportedFormats() {
        System.err.println("Following formats are supported:");
        for (String format: FORMATS) {
            System.err.println("* " + format);
        }
    }
}
