import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main{
    public static void main(String[] args){
        if(args.length <= 0){
            System.out.println("No arguments given");
            return;
        }
        if(args[0]=="-r"){
            if(args.length < 11){
                System.out.println("Not enough arguments given");
                return;
            } else if(args.length > 11){
                System.out.println("Too many arguments given");
                return;
            } else if(args.length == 11){
                int n = Integer.parseInt(args[1]);
                int a = Integer.parseInt(args[2]);
                int n1 = Integer.parseInt(args[3]);
                int alpha = Integer.parseInt(args[4]);
                int beta = Integer.parseInt(args[5]);
                int delta = Integer.parseInt(args[6]);
                int eta = Integer.parseInt(args[7]);
                int rho = Integer.parseInt(args[8]);
                int gamma = Integer.parseInt(args[9]);
                int nu = Integer.parseInt(args[10]);
                int tau = Integer.parseInt(args[11]);


            }
        } else if(args[0]=="-f")
        
    }
}