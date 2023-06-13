package main.utils;

import java.io.FileNotFoundException;

public class ConsoleFilePrinters extends Printer {
    private static ConsoleFilePrinters instance;
    private ConsolePrinter consolePrinter;
    private FilePrinter filePrinter;

    private ConsoleFilePrinters(String fileName) throws FileNotFoundException {
        this.consolePrinter = new ConsolePrinter();
        this.filePrinter = new FilePrinter(fileName);
    }

    public static synchronized void initialize(String fileName) throws FileNotFoundException {
        if (instance == null) {
            instance = new ConsoleFilePrinters(fileName);
        } else {
            throw new RuntimeException("MultiPrinter has already been initialized.");
        }
    }

    public static synchronized ConsoleFilePrinters getInstance() {
        if (instance == null) {
            throw new RuntimeException("MultiPrinter has not been initialized. Call initialize() before using getInstance().");
        }
        return instance;
    }

    @Override
    public void println(String text) {
        consolePrinter.println(text);
        filePrinter.println(text);
    }

    @Override
    public void printf(String format, Object... args) {
        consolePrinter.printf(format, args);
        filePrinter.printf(format, args);
    }

    // Add overrides for other methods as needed...
}
