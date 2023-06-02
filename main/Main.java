package main;

//import java.io.PrintStream;
//import java.io.UnsupportedEncodingException;

import ant.AntColony;
import cycle.Cycle;

public class Main {
	public static void main(String[] args){
		/*try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setErr(new PrintStream(System.err, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // Handle the exception
            e.printStackTrace();
        }*/
        try{
            ParameterReader.readingMode(args[0]);
        } catch(ArrayIndexOutOfBoundsException e){
            Message.CommandNotFound("Too few arguments", "java -jar project.jar");
        }
        if(ParameterReader.getReadingMode() == 0){
            if(args.length < 12){
                Message.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 12){
                Message.CommandNotFound("Too many arguments", args[0]);
            } else {
                ParameterReader.readParameters(args);
            }
        }
        else if(ParameterReader.getReadingMode() == 1){
            if(args.length < 2){
                Message.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 2){
                Message.CommandNotFound("Too many arguments", args[0]);
            } else {
                ParameterReader.readInputFile(args[1]);
                //WeightedGraph_di graph = new WeightedGraph_di(ParameterReader.n);
                //graph.printAdjacencyMatrix();
            }
        }
        ParameterReader.printParameters();
        AntColony antColony = new AntColony();
        Cycle cycle = new Cycle();
        cycle.addNode(ParameterReader.getNest());
        cycle.incrementCurrentCycleWeight(2);
        System.out.println("Last Node in Cycle:"+cycle.getLastNode());
        System.out.println("Current Cycle Weight:"+cycle.getCurrentCycleWeight());

        antColony.addCycleToPQ(cycle);
    }
}
