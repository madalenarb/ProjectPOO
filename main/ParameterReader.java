package main;

import java.io.BufferedReader;

import graph.GraphFacade;
import main.utils.PrinterController;
import main.utils.MessageError;

import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used to read the parameters from the command line or from a file.
 *
 * <p>The parameters include:</p>
 * <ul>
 *   <li>{@code readingMode}: The reading mode (0 for -r, 1 for -f).</li>
 *   <li>{@code n}: The number of nodes in the graph.</li>
 *   <li>{@code a}: The nest node.</li>
 *   <li>{@code n1}: The weight of traversing an edge.</li>
 *   <li>{@code alpha}: The parameter concerning the ant movements.</li>
 *   <li>{@code beta}: The parameter concerning the ant movements.</li>
 *   <li>{@code delta}: The parameter concerning the ant movements.</li>
 *   <li>{@code eta}: The parameter concerning the pheromone evaporation.</li>
 *   <li>{@code rho}: The parameter concerning the pheromone evaporation.</li>
 *   <li>{@code gamma}: The parameter concerning the pheromone evaporation.</li>
 *   <li>{@code nu}: The ant colony size.</li>
 *   <li>{@code tau}: The final instant of the simulation.</li>
 * </ul>
 *
 * @return The parameters.
 */

public class ParameterReader {
	static int readingMode; // 0 for -r, 1 for -f
    static int n; 
    static int a; 
    static int n1; 
    static float alpha; 
    static float beta;
    static float delta;    
    static float eta;  
    static float rho;  
    static float gamma;    
    static int nu;   
    static float tau;

    /**
     * Sets the reading mode based on the provided mode argument.
     * 
     * @param mode the mode argument (-r or -f)
     */
    public static void readingMode(String mode){
        if(mode.contains("-r")){
            readingMode = 0;
        } else if(mode.contains("-f")){
            readingMode = 1;
        } else {
            MessageError.CommandNotFound("Reading Mode not found", mode);
        }
    }

    /**
     * Returns the reading mode.
     * 
     * @return the reading mode (0 for -r, 1 for -f)
     */
    public static int getReadingMode(){
        return readingMode;
    }
    
    /**
     * Gets the number of nodes in the graph.
     * 
     * @return the number of nodes in the graph
     */
    public static int getN() {
    	return n;
    }
    
    public static int getMaxWeight(){
        return a;
    }

    /**
     * Gets the nest node.
     * 
     * @return the nest node
     */
    public static int getNest() {
    	return n1;
    }
    
    /**
     * Gets the alpha parameter for the ant movements.
     * 
     * @return the alpha parameter
     */
    public static float getAlpha() {
    	return alpha;
    }
    
    /**
     * Gets the beta parameter for the ant movements.
     * 
     * @return the beta parameter
     */
    public static float getBeta() {
    	return beta;
    }
    
    /**
     * Returns the delta parameter for ant movements.
     * 
     * @return the delta parameter
     */
    public static float getDelta() {
    	return delta;
    }
    
    /**
     * Returns the rho parameter for pheromone evaporation.
     * 
     * @return the rho parameter
     */
    public static float getRho() {
    	return rho;
    }
    
    /**
     * Returns the eta parameter for pheromone evaporation.
     * 
     * @return the eta parameter
     */
    public static float getEta() {
    	return eta;
    }
    
    /**
     * Returns the gamma parameter for pheromone evaporation.
     * 
     * @return the gamma parameter
     */
    public static float getGamma() {
    	return gamma;
    }
    
    /**
     * Returns the ant colony size.
     * 
     * @return the ant colony size
     */
    public static int getNu() {
    	return nu;
    }

    /**
     * Returns the final instant of the simulation.
     * 
     * @return the final instant
     */
    public static float getTau() {
    	return tau;
    }
    

    /**
     * Reads the parameters from the command line arguments.
     * 
     * @param args the command line arguments
     */
    public static void readParameters(String[] args){
        try{
            n = Integer.parseInt(args[1]);
            a = Integer.parseInt(args[2]);
            n1 = Integer.parseInt(args[3])-1;
            nu = Integer.parseInt(args[10]);
        } catch (NumberFormatException e){
            MessageError.InvalidArgument("Wrong argument type for n, a, n1, or nu. They must be integers.");
        } 
    
        try{
            alpha = Float.parseFloat(args[4]);
            beta = Float.parseFloat(args[5]);
            delta = Float.parseFloat(args[6]);
            eta = Float.parseFloat(args[7]);
            rho = Float.parseFloat(args[8]);
            gamma = Float.parseFloat(args[9]);
            tau = Float.parseFloat(args[11]);
        } catch (NumberFormatException e){
            MessageError.InvalidArgument("Wrong argument type for alpha, beta, delta, eta, rho, gamma, or tau. They must be floats.");
        }
        verifyParameters(false);
        GraphFacade g = GraphFacade.getInstance();
        g.fillGraphReadMode(n, a);
    }

    /**
     * Reads the parameters from the specified input file.
     * 
     * @param inputFile the file from which the parameters are read
     */
    public static void readInputFile(String inputFile){
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            int lineCount = 0;
            String line = br.readLine();
            GraphFacade g = null;
    
            while(line != null){
                if(lineCount == 0){
                    String[] parameters = line.trim().split("\\s+");
                    int numberofParameters = parameters.length;
                    if(parameters.length != 10){
                        MessageError.WrongNumberOfArguments("Wrong number of arguments in the input file, it must be 10, instead of " + numberofParameters + "");
                    }
    
                    try{
                        n = Integer.parseInt(parameters[0]);
                        n1 = Integer.parseInt(parameters[1])-1;
                        nu = Integer.parseInt(parameters[8]);
                    } catch (NumberFormatException e){
                        MessageError.InvalidArgument("Wrong argument type for n, n1, or nu in the input file. They must be integers.");
                    } 
    
                    try{
                        alpha = Float.parseFloat(parameters[2]);
                        beta = Float.parseFloat(parameters[3]);
                        delta = Float.parseFloat(parameters[4]);
                        eta = Float.parseFloat(parameters[5]);
                        rho = Float.parseFloat(parameters[6]);
                        gamma = Float.parseFloat(parameters[7]);
                        tau = Float.parseFloat(parameters[9]);
                    } catch (NumberFormatException e){
                        MessageError.InvalidArgument("Wrong argument type for alpha, beta, delta, eta, rho, gamma, or tau in the input file. They must be floats.");
                    }
    
                    verifyParameters(true);
    
                    g = GraphFacade.getInstance();
                } 
                else {
                    try{
                        String[] weights = line.trim().split("\\s+");
                        if(weights.length != n){
                            MessageError.WrongNumberOfArguments("Wrong number of edges in the input file, it must be " + n + " instead of " + weights.length + "");
                        }
                        if(g != null) {
                            g.fillGraphFileMode(weights, lineCount);
                        }
                    } catch (NumberFormatException e){
                        MessageError.InvalidArgument("Wrong argument type for the weights in the input file. They must be integers.");
                    }
                }
                lineCount++;
                line = br.readLine();
            }
        } catch (IOException e) {
            MessageError.FileNotFoundWithInput(inputFile);
        }      
    
        GraphFacade.getInstance().checkGraphSymmetry();
        if(GraphFacade.getInstance().isDisconnected()){
            MessageError.InvalidArgument("The graph is invalid, the graph isn't connected");
        }
    }
    
    
    /**
     * Verifies if the parameters are valid.
     */
    public static void verifyParameters(boolean FileMode) {
    	if(n < 2) {
    		MessageError.InvalidArgument("Invalid argument, n must be greater than 1");
    	}
        if(a < 1 && !FileMode) {
            MessageError.InvalidArgument("Invalid argument, a must be greater than 0");
        }
    	if(n1 < 0) {
    		MessageError.InvalidArgument("Invalid argument, n1 must be greater than 0");
    	}
    	if(alpha <= 0) {
    		MessageError.InvalidArgument("Invalid argument, alpha must be greater than 0");
    	}
    	if(beta <= 0){
    		MessageError.InvalidArgument("Invalid argument in the input file, beta must be greater than 0");
    	}
    	if(delta <= 0) {
    		MessageError.InvalidArgument("Invalid argument in the input file, delta must be greater than 0");
    	}
    	if(eta <= 0) {
    		MessageError.InvalidArgument("Invalid argument in the input file, eta must be greater or equal than 0");
    	}
    	if(rho <= 0) {
    		MessageError.InvalidArgument("Invalid argument in the input file, rho must be greater or equal than 0"); 
    	}
    	if(gamma <= 0 ) {
    		MessageError.InvalidArgument("Invalid argument in the input file, gamma must be greater than 0");
    	}
    	if(nu < 1) {
    		MessageError.InvalidArgument("Invalid argument in the input file, nu must be greater than 0");
    	}
    	if(tau <= 0) {
    		MessageError.InvalidArgument("Invalid argument in the input file, tau must be greater than 0");
    	}
    }

    /**
     * Prints the input parameters and the graph.
     */
    public static void printParameters() {
        int nest = n1 + 1;
        PrinterController.getInstance().println("Input parameters:");
        PrinterController.getInstance().println("\t\t\t" + n + ": number of nodes in the graph");
        PrinterController.getInstance().println("\t\t\t" + nest + " : the nest node");
        PrinterController.getInstance().println("\t\t\t" + alpha + " : alpha, ant move event");
        PrinterController.getInstance().println("\t\t\t" + beta + " : beta, ant move event");
        PrinterController.getInstance().println("\t\t\t" + delta + " : delta, ant move event");
        PrinterController.getInstance().println("\t\t\t" + eta + " : eta, pheromone evaporation event");
        PrinterController.getInstance().println("\t\t\t" + rho + " : rho, pheromone evaporation event");
        PrinterController.getInstance().println("\t\t\t" + gamma + " : pheromone level");
        PrinterController.getInstance().println("\t\t\t" + nu + " : ant colony size");
        PrinterController.getInstance().println("\t\t\t" + tau + " : final instant");
        PrinterController.getInstance().println("\twith graph:");
        GraphFacade.getInstance().printAntGraph();
        PrinterController.getInstance().println("");
    }
    
}
