package main.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * The FilePrinter class implements the Printer interface and provides functionality for printing text to a file.
 */
public class FilePrinter implements Printer {
    private PrintStream printStream;

    /**
     * Constructs a new FilePrinter with the specified file name.
     *
     * @param fileName The name of the file to print to.
     * @throws FileNotFoundException If the file specified by fileName is not found.
     */
    public FilePrinter(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        this.printStream = new PrintStream(new FileOutputStream(file));
    }

    /**
     * Prints a line of text to the file.
     *
     * @param text The text to print.
     */
    @Override
    public void println(String text) {
        printStream.println(text);
    }

    /**
     * Prints formatted text to the file.
     *
     * @param format The format string.
     * @param args   The arguments referenced by the format string.
     */
    @Override
    public void printf(String format, Object... args) {
        printStream.printf(format, args);
    }
}
