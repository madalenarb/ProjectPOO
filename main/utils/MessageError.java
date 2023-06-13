package main.utils;

/**
 * The MessageError class provides methods for printing error messages and the correct usage instructions.
 */
public class MessageError {
    /**
     * Throws an exception for a command not found.
     *
     * @param message The error message.
     * @param command The invalid command.
     */
    public static void CommandNotFound(String message, String command) {
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + ": " + command + "\n \n\n" +
                CorrectUsage());
        System.exit(1);
    }

    /**
     * Throws an exception for the wrong number of arguments.
     *
     * @param message The error message.
     */
    public static void WrongNumberOfArguments(String message) {
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + "\n"
                + CorrectUsage());
        System.exit(1);
    }

    /**
     * Throws an exception for a file not found.
     *
     * @param filePath The invalid file path.
     */
    public static void FileNotFoundWithInput(String filePath) {
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + "File not found: " + filePath + "\n \n\n" +
                CorrectUsage());
        System.exit(1);
    }

    /**
     * Throws an exception for a file extension not supported.
     *
     * @param message  The error message.
     * @param filePath The invalid file path.
     */
    public static void FileExtensionNotSupported(String message, String filePath) {
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + ": " + filePath + "\n \n\n" +
                CorrectUsage());
        System.exit(1);
    }

    /**
     * Throws an exception for a file format not supported.
     *
     * @param message    The error message.
     * @param inputFile  The input file.
     */
    public static void FileFormatNotSupported(String message, String inputFile) {
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + ": " + inputFile + "\n \n\n" +
                CorrectUsage());
        System.exit(1);
    }

    /**
     * Returns the correct usage instructions for the program.
     *
     * @return The correct usage instructions.
     */
    public static String CorrectUsage() {
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
