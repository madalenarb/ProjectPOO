package main;

public class Main{
    public static void main(String[] args){
        ParameterReader.readingMode(args[0]);
        if(ParameterReader.getReadingMode() == 0){
            if(args.length < 12){
                ErrorClass.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 12){
                ErrorClass.CommandNotFound("Too many arguments", args[0]);
            } else {
                ParameterReader.readParameters(args);
            }
        } else if(ParameterReader.getReadingMode() == 1){
            if(args.length < 2){
                ErrorClass.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 2){
                ErrorClass.CommandNotFound("Too many arguments", args[0]);
            } else {
                ParameterReader.readInputFile(args[1]);
            }
        }
    }
}