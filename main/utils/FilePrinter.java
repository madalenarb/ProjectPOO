package main.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FilePrinter implements Printer {
    private PrintStream printStream;

    public FilePrinter(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if(!file.exists()){
            try{
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        this.printStream = new PrintStream(new FileOutputStream(file));
    }

    @Override
    public void println(String text) {
        printStream.println(text);
    }

    @Override
    public void printf(String format, Object... args) {
        printStream.printf(format, args);
    }
}
