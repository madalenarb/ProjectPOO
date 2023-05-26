package main;

//import java.io.PrintStream;
//import java.io.UnsupportedEncodingException;

public class Main {
	public static void main(String[] args){
		/*try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setErr(new PrintStream(System.err, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // Handle the exception
            e.printStackTrace();
        }*/
        ParameterReader.readingMode(args[0]);
        if(ParameterReader.getReadingMode() == 0){
            if(args.length < 12){
                ErrorClass.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 12){
                ErrorClass.CommandNotFound("Too many arguments", args[0]);
            } else {
                ParameterReader.readParameters(args);
            }
        }
        else if(ParameterReader.getReadingMode() == 1){
            if(args.length < 2){
                ErrorClass.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 2){
                ErrorClass.CommandNotFound("Too many arguments", args[0]);
            } else {
                ParameterReader.readInputFile(args[1]);
                //WeightedGraph_di graph = new WeightedGraph_di(ParameterReader.n);
                //graph.printAdjacencyMatrix();
            }
        }
        ParameterReader.printParameters();
    }
}
