package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


// separar main num outro package 
// colocar o readFile no main package como uma classe separada

public class Main{
    public static void main(String[] args){
        if(args.length <= 0){
            System.out.println("No arguments given");
            return;
        }
        if(args[0]=="-r"){
            if(args.length < 12){
                System.out.println("Not enough arguments given");
                return;
            } else if(args.length > 12){
                System.out.println("Too many arguments given");
                return;
            } else if(args.length == 12){
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
        } else if(args[0]=="-f")
        {
            if(args.length > 1){
                System.out.println("Too many arguments given");
                return;
            } else {
                String inputFile = args[1];

                try(BufferedReader reader = new BufferedReader(new FileReader(inputFile));){
                    String line = reader.readLine();
                    if(line != null){
                        String[] variablesString = line.split(" ");
                        if(variablesString.length < 10){
                            System.out.println("Not enough arguments given");
                            return;
                        } else if(variablesString.length > 10){
                            System.out.println("Too many arguments given");
                            return;
                        } else {
                            int n = Integer.parseInt(variablesString[0]); // Number of nodes in the graph
                            int a = Integer.parseInt(variablesString[1]);  // the nest node
                            int n1 = Integer.parseInt(variablesString[2]); // weight of traversing an edge
                            float alpha = Integer.parseInt(variablesString[3]); // parameter concerning the ant movements
                            float beta = Integer.parseInt(variablesString[4]); // parameter concerning the ant movements
                            float delta = Integer.parseInt(variablesString[5]);    // parameter concerning the ant movements
                            float eta = Integer.parseInt(variablesString[6]);  // parameter concerning the pheromone evaporation
                            float rho = Integer.parseInt(variablesString[7]);  // parameter concerning the pheromone evaporation
                            float gamma = Integer.parseInt(variablesString[8]);    // parameter concerning pheromone level
                            int nu = Integer.parseInt(variablesString[9]);    // ant colony size
                            float tau = Integer.parseInt(variablesString[10]); //final instant of the simulation
                        }
                    } else {
                        System.out.println("File is empty");
                        return;
                    }
                } catch (IOException e){
                    System.out.println("Error reading file");
                    return;
                }
            }
        }
    }
}