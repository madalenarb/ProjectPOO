package main;

import java.io.FileNotFoundException;

/**
 * The Message class provides methods for printing error messages and the correct usage instructions.
 */
class Message {
	/**
     * Throws an exception for a command not found.
     *
     * @param message the error message
     * @param command the invalid command
     * @throws IllegalArgumentException
     */
    public static void CommandNotFound(String message, String command) throws IllegalArgumentException{
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + ": " + command + "\n \n\n" +
                CorrectUsage());
        throw new IllegalArgumentException(message);
    }

    /**
     * Throws an exception for the wrong number of arguments.
     *
     * @param message the error message
     * @throws IllegalArgumentException
     */
    public static void WrongNumberOfArguments(String message) throws IllegalArgumentException{
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + "\n"
                + CorrectUsage());
        throw new IllegalArgumentException(message);
    }

    /**
     * Throws an exception for a file not found.
     *
     * @param filePath the invalid file path
     * @throws FileNotFoundException
     */
    public static void FileNotFoundWithInput(String filePath) throws FileNotFoundException {
        try {
            throw new FileNotFoundException("File not found: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
            System.out.println(CmdTextFormatter.RESET + e.getMessage() + ": " + filePath + "\n \n\n" +
                    CorrectUsage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Throws an exception for a file extension not supported.
     *
     * @param message the error message
     * @param filePath the invalid file path
     * @throws IllegalArgumentException
     */    
    public static void FileExtensionNotSupported(String message, String filePath) throws IllegalArgumentException{
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + ": " + filePath + "\n \n\n" +
                CorrectUsage());
        throw new IllegalArgumentException(message);
    }

    /**
     * Returns the correct usage instructions for the program.
     *
     * @return the correct usage instructions
     */
    public static String CorrectUsage(){
        String usage = CmdTextFormatter.GREEN + "Correct usage: " + CmdTextFormatter.RESET + "\n" +
                "java -jar project.jar -f <inputFile>" + "\n" +
                "\njava -jar project.jar -r <n> <a> <n1> <alpha> <beta> <delta> <eta> <rho> <gamma> <nu> <tau>" + "\n" +
                "n:\t number of nodes in the graph" + "\n" +
                "a:\t the nest node" + "\n" +
                "n1:\t weight of traversing an edge" + "\n" +
                "alpha:\t parameter concerning the ant movements" + "\n" +
                "beta:\t parameter concerning the ant movements" + "\n" +
                "delta:\t parameter concerning the ant movements" + "\n" +
                "eta, rho, gamma:\t parameter concerning the pheromone evaporation" + "\n" +
                "nu:\t ant colony size" + "\n" +
                "tau:\t final instant of the simulation";
        return usage;
    }
}
