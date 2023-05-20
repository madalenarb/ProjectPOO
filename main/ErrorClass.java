package main;

public class ErrorClass {
    public static void CommandNotFound(String message, String command){
        System.out.println(cmdTextFormatter.format("(!!!) WARNING " + message + ": " + command, "RED") + "\n" +
        cmdTextFormatter.format("Correct usage: ", "GREEN"));
        System.exit(0);
    }

    public static void WrongNumberOfArguments(String message){
        System.out.println(cmdTextFormatter.format("(!!!) WARNING " + message, "RED") + "\n"
         + cmdTextFormatter.format("Correct usage: ", "GREEN") + "\n"
         + CorrectUsage());
        System.exit(0);
    }

    public static String CorrectUsage(){
        String usage = cmdTextFormatter.format("Correct usage: ", "GREEN") + "\n" + 
        cmdTextFormatter.format("java -jar project.jar -r <n> <a> <n1> <alpha> <beta> <delta> <eta> <rho> <gamma> <nu> <tau>", "CYAN") + "\n" + 
        "n:\t number of nodes in the graph" + "\n" +
        "a:\t the nest node" + "\n" +
        "n1:\t weight of traversing an edge" + "\n" +
        "alpha:\t parameter concerning the ant movements" + "\n" +
        "beta:\t parameter concerning the ant movements" + "\n" +
        "delta:\t parameter concerning the ant movements" + "\n" +
        "eta, rho, gamma:\t parameter concerning the pheromone evaporation" + "\n" +
        "nu:\t ant colony size" + "\n" +
        "tau:\t final instant of the simulation" + "\n" +
        cmdTextFormatter.format("java -jar project.jar -f <inputFile>", "CYAN");
        return usage;
    }
}
