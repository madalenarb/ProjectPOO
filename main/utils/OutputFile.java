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
    public static void initialize(String inputFile, boolean isFile) {
        // Create the SIM folder if it doesn't exist
        File simFolder = new File("SIM");
        // If the directory does not exist, create it
        if (!simFolder.exists()) {
            simFolder.mkdir();
        }
        
        String outputFile;
        if(isFile){
            outputFile = generateOutputFileNameFromInputFile(inputFile);
        } else {
            outputFile = generateOutputFileName();
        }

        try {
            ConsoleFilePrinters.initialize(outputFile, PrinterType.BOTH);
        } catch (FileNotFoundException e) {
            MessageError.FileNotFoundWithInput(outputFile);
        }
    }
    
    /**
     * Generates an output file name when the program is run with the -r command.
     * The method will generate a name like simscenarioX.txt, where X is a number that
     * is incremented until a non-existing file is found.
     * 
     * @return the name of the output file
     */
    public static String generateOutputFileName(){
        int i = 1;
        String fileName;
        File file;
        do{
            fileName = "SIM/simscenario" + i + ".txt";
            file = new File(fileName);
            i++;
        } while (file.exists() && file.length() != 0);
        return fileName;
    }

    /**
     * Generates an output file name based on the input file name.
     * Used when the program is run with the -f command.
     * 
     * @param inputFileName the name of the input file
     * @return the name of the output file
     */
    public static String generateOutputFileNameFromInputFile(String inputFile) {
        if (!inputFile.endsWith(".txt")) {
            MessageError.FileExtensionNotSupported("File extension must be .txt", inputFile);
        }
        File file = new File(inputFile.replaceAll("\\s", ""));
        String fileName = file.getName();
        if (!fileName.matches("input\\d+\\.txt")) {
            MessageError.FileFormatNotSupported("File Format is wrong", fileName);
        }
        String OutputFile = fileName.replaceFirst("input(\\d+)\\.txt", "simscenario$1.txt");
        return "SIM/" + OutputFile;
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
