package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParameterReader {
    int n; // Number of nodes in the graph
    int a;  // the nest node
    int n1; // weight of traversing an edge
    float alpha; // parameter concerning the ant movements
    float beta; // parameter concerning the ant movements
    float delta;    // parameter concerning the ant movements
    float eta;  // parameter concerning the pheromone evaporation
    float rho;  // parameter concerning the pheromone evaporation
    float gamma;    // parameter concerning pheromone level
    int nu;    // ant colony size
    float tau; //final instant of the simulation

    String inputFile;

    public static void main(String[] args){
        if(args[0] == "-r"){
            if(args.length < 11){
                ErrorClass.WrongNumberOfArguments("Not enough arguments given");
            } else if(args.length > 11){
                ErrorClass.WrongNumberOfArguments("Too many arguments given");
            } else {
                int n = Integer.parseInt(args[1]); // Number of nodes in the graph
                int a = Integer.parseInt(args[2]);  // the nest node
                int n1 = Integer.parseInt(args[3]); // weight of traversing an edge
                float alpha = Integer.parseInt(args[4]); // parameter concerning the ant movements
                float beta = Integer.parseInt(args[5]); // parameter concerning the ant movements
                float delta = Integer.parseInt(args[6]);    // parameter concerning the ant movements
                float eta = Integer.parseInt(args[7]);  // parameter concerning the pheromone evaporation
                float rho = Integer.parseInt(args[8]);  // parameter concerning the pheromone evaporation
                float gamma = Integer.parseInt(args[9]);    // parameter concerning pheromone level
                int nu = Integer.parseInt(args[10]);    // ant colony size
                float tau = Integer.parseInt(args[11]); //final instant of the simulation
            }
        } else if(args[0] == "-f"){
            if(args.length > 1){
                ErrorClass.WrongNumberOfArguments("Too many arguments given");
            } else if(args.length < 1){
                ErrorClass.WrongNumberOfArguments("Not enough arguments given");
            } else {
                String inputFile = args[1];
            }
        }
    }
}
