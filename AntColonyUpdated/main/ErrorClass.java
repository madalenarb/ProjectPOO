package main;

class ErrorClass {
	/**
     * Prints an error message for a command not found and exits the program.
     *
     * @param message the error message
     * @param command the invalid command
     */
    public static void CommandNotFound(String message, String command){
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + ": " + command + "\n \n\n" +
                CorrectUsage());
        System.exit(0);
    }

    /**
     * Prints an error message for the wrong number of arguments and exits the program.
     *
     * @param message the error message
     */
    public static void WrongNumberOfArguments(String message){
        System.out.println(CmdTextFormatter.RED + "(!!!) WARNING ");
        System.out.println(CmdTextFormatter.RESET + message + "\n"
                + CorrectUsage());
        System.exit(0);
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
