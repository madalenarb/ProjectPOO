package main.utils;

public class ConsolePrinter implements Printer {
    public ConsolePrinter() {
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void printf(String format, Object... args) {
        System.out.printf(format, args);
    }
}
