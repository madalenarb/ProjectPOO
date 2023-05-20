package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParameterReader {
    static int readingMode; // 0 for -r, 1 for -f
    static int n; // Number of nodes in the graph
    static int a;  // the nest node
    static int n1; // weight of traversing an edge
    static float alpha; // parameter concerning the ant movements
    static float beta; // parameter concerning the ant movements
    static float delta;    // parameter concerning the ant movements
    static float eta;  // parameter concerning the pheromone evaporation
    static float rho;  // parameter concerning the pheromone evaporation
    static float gamma;    // parameter concerning pheromone level
    static int nu;    // ant colony size
    static float tau; //final instant of the simulation

    String inputFile;

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

    public static int getReadingMode(){
        return readingMode;
    }

    
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
