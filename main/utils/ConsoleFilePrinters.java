package main.utils;

import java.io.FileNotFoundException;

/**
 * The ConsoleFilePrinters class provides functionality for printing text to both the console and a file.
 * It supports different printer types, such as console-only, file-only, or both.
 */
public class ConsoleFilePrinters {
    private static ConsoleFilePrinters instance;
    private Printer consolePrinter;
    private Printer filePrinter;
    private PrinterType printerType;

    /**
     * Private constructor for ConsoleFilePrinters.
     * Use the initialize method to create an instance of ConsoleFilePrinters.
     *
     * @param fileName    The name of the file to print to.
     * @param type        The type of printer (CONSOLE, FILE, or BOTH).
     * @throws FileNotFoundException If the file specified by fileName is not found.
     */
    private ConsoleFilePrinters(String fileName, PrinterType type) throws FileNotFoundException {
        this.consolePrinter = new ConsolePrinter();
        this.filePrinter = new FilePrinter(fileName);
        this.printerType = type;
    }


    /**
     * Initializes the ConsoleFilePrinters class with the specified file name and printer type.
     *
     * @param fileName    The name of the file to print to.
     * @param type        The type of printer (CONSOLE, FILE, or BOTH).
     * @throws FileNotFoundException If the file specified by fileName is not found.
     */
    public static synchronized void initialize(String fileName, PrinterType type) throws FileNotFoundException {
        if (instance == null) {
            instance = new ConsoleFilePrinters(fileName, type);
        } else {
            throw new RuntimeException("ConsoleFilePrinters has already been initialized.");
        }
    }

    /**
     * Gets the singleton instance of ConsoleFilePrinters.
     *
     * @return The singleton instance of ConsoleFilePrinters.
     */
    public static synchronized ConsoleFilePrinters getInstance() {
        if (instance == null) {
            throw new RuntimeException("ConsoleFilePrinters has not been initialized. Call initialize() before using getInstance().");
        }
        return instance;
    }

    /**
     * Prints a line of text to both the console and the file (if enabled).
     *
     * @param text The text to print.
     */
    public void println(String text) {
        if (printerType == PrinterType.CONSOLE || printerType == PrinterType.BOTH) {
            consolePrinter.println(text);
        }
        if (printerType == PrinterType.FILE || printerType == PrinterType.BOTH) {
            filePrinter.println(text);
        }
    }

    /**
     * Prints formatted text to both the console and the file (if enabled).
     *
     * @param format The format string.
     * @param args   The arguments referenced by the format string.
     */
    public void printf(String format, Object... args) {
        if (printerType == PrinterType.CONSOLE || printerType == PrinterType.BOTH) {
            consolePrinter.printf(format, args);
        }
        if (printerType == PrinterType.FILE || printerType == PrinterType.BOTH) {
            filePrinter.printf(format, args);
        }
    }
}
