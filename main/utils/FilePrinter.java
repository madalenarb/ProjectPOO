package main.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FilePrinter extends Printer {
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
}
