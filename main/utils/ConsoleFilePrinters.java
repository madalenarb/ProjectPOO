package main.utils;

import java.io.FileNotFoundException;

public class ConsoleFilePrinters {
    private static ConsoleFilePrinters instance;
    private Printer consolePrinter;
    private Printer filePrinter;
    private PrinterType printerType;

    private ConsoleFilePrinters(String fileName, PrinterType type) throws FileNotFoundException {
        this.consolePrinter = new ConsolePrinter();
        this.filePrinter = new FilePrinter(fileName);
        this.printerType = type;
    }

    public static synchronized void initialize(String fileName, PrinterType type) throws FileNotFoundException {
        if (instance == null) {
            instance = new ConsoleFilePrinters(fileName, type);
        } else {
            throw new RuntimeException("ConsoleFilePrinters has already been initialized.");
        }
    }

    public static synchronized ConsoleFilePrinters getInstance() {
        if (instance == null) {
            throw new RuntimeException("ConsoleFilePrinters has not been initialized. Call initialize() before using getInstance().");
        }
        return instance;
    }

    public void println(String text) {
        if (printerType == PrinterType.CONSOLE || printerType == PrinterType.BOTH) {
            consolePrinter.println(text);
        }
        if (printerType == PrinterType.FILE || printerType == PrinterType.BOTH) {
            filePrinter.println(text);
        }
    }

    public void printf(String format, Object... args) {
        if (printerType == PrinterType.CONSOLE || printerType == PrinterType.BOTH) {
            consolePrinter.printf(format, args);
        }
        if (printerType == PrinterType.FILE || printerType == PrinterType.BOTH) {
            filePrinter.printf(format, args);
        }
    }
}
