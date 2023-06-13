package main.utils;

/**
 * The Printer interface provides methods for printing text.
 */
public interface Printer {
    /**
     * Prints a line of text.
     *
     * @param text The text to print.
     */
    void println(String text);

    /**
     * Prints formatted text.
     *
     * @param format The format string.
     * @param args   The arguments referenced by the format string.
     */
    void printf(String format, Object... args);
}
