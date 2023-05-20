package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
 * This class is used to read the parameters from the command line or from a file
 * 
 * @param readingMode: 0 for -r, 1 for -f
 * @param n: number of nodes in the graph
 * @param a: the nest node
 * @param n1: weight of traversing an edge
 * @param alpha: parameter concerning the ant movements
 * @param beta: parameter concerning the ant movements
 * @param delta: parameter concerning the ant movements
 * @param eta: parameter concerning the pheromone evaporation
 * @param rho: parameter concerning the pheromone evaporation
 * @param gamma: parameter concerning the pheromone evaporation
 * @param nu: ant colony size
 * @param tau: final instant of the simulation
 * 
 * @return: the parameters
 * 
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

    String inputFile;

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
            ErrorClass.CommandNotFound("Command not found", mode);
            System.exit(0);
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
     * Reads the parameters from the command line arguments.
     * 
     * @param args the command line arguments
     */
    public static void readParameters(String[] args){
        n = Integer.parseInt(args[1]);
        a = Integer.parseInt(args[2]);
        n1 = Integer.parseInt(args[3]);
        alpha = Float.parseFloat(args[4]);
        beta = Float.parseFloat(args[5]);
        delta = Float.parseFloat(args[6]);
        eta = Float.parseFloat(args[7]);
        rho = Float.parseFloat(args[8]);
        gamma = Float.parseFloat(args[9]);
        nu = Integer.parseInt(args[10]);
        tau = Float.parseFloat(args[11]);
    }

    /**
     * Reads the parameters from the specified input file.
     * 
     * @param inputFile the file from which the parameters are read
     */
    public static void readInputFile(String inputFile){
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))){
            String line;
            while((line = br.readLine()) != null){
                String[] parameters = line.split(" ");
                n = Integer.parseInt(parameters[1]);
                n1 = Integer.parseInt(parameters[2]);
                alpha = Float.parseFloat(parameters[3]);
                beta = Float.parseFloat(parameters[4]);
                delta = Float.parseFloat(parameters[5]);
                eta = Float.parseFloat(parameters[6]);
                rho = Float.parseFloat(parameters[7]);
                gamma = Float.parseFloat(parameters[8]);
                nu = Integer.parseInt(parameters[9]);
                tau = Float.parseFloat(parameters[10]);
            }
        } catch(IOException e){
            System.out.println("Error reading file");
            System.exit(0);
        }
    }
}
