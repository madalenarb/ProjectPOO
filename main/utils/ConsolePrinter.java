package main.utils;

/**
 * The ConsolePrinter class implements the Printer interface and provides functionality for printing text to the console.
 */
public class ConsolePrinter implements Printer {
    /**
     * Constructs a new ConsolePrinter.
     */
    public ConsolePrinter() {
    }

    /**
     * Prints a line of text to the console.
     *
     * @param text The text to print.
     */
    @Override
    public void println(String text) {
        System.out.println(text);
    }

    /**
     * Prints formatted text to the console.
     *
     * @param format The format string.
     * @param args   The arguments referenced by the format string.
     */
    @Override
    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
