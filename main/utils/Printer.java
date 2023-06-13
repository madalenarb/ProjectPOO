package main.utils;

import java.io.PrintStream;

/**
 * The Printer class provides methods for printing the results of the simulation.
 */
public abstract class Printer {
    protected PrintStream printStream;

    public void println(String line) {
        printStream.println(line);
    }

    public void printf(String format, Object... args) {
        printStream.printf(format, args);
    }
}