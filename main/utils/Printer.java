package main.utils;

public interface Printer {
    void println(String text);
    void printf(String format, Object... args);
}