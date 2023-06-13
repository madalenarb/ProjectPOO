package main.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


/**
 * The OutputFile class provides methods for printing the results of the simulation.
 */
public class OutputFile {
    private static PrintStream printStream;

    /**
     * Initializes the OutputFile class.
     *
     * @param inputFile The name of the input file.
     * @throws FileNotFoundException If the file is not found.
     */
    public static void initialize(String inputFile) {
        File file = new File(inputFile.replaceAll("\\s", ""));
        String fileName = file.getName();
        if (!fileName.matches("input\\d+\\.txt")) {
            MessageError.FileFormatNotSupported("File Format is wrong", fileName);
        }
    
        // Create the SIM folder if it doesn't exist
        File simFolder = new File("SIM");
        // If the directory does not exist, create it
        if (!simFolder.exists()) {
            simFolder.mkdir();
            System.out.println("SIM folder created");
        }
        String outputFile = "SIM/" + fileName.replaceFirst("input(\\d+)\\.txt", "simscenario$1.txt");
        System.out.println("Output file: " + outputFile);
        try {
            System.out.println("Output file: " + outputFile);
            ConsoleFilePrinters.initialize(outputFile, PrinterType.BOTH);
        } catch (FileNotFoundException e) {
            MessageError.FileNotFoundWithInput(outputFile);
        }
    }
    

    /**
     * Closes the output file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public static void close() throws IOException {
        if (printStream != null) {
            printStream.close();
        }
    }
}
