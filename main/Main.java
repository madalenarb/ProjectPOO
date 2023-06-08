package main;

import event.EventManager;

public class Main {
	public static void main(String[] args){
		
        ParameterReader.readingMode(args[0]);
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
            }
        }
        
        ParameterReader.printParameters();
        
        EventManager eventManager = EventManager.getInstance();
        eventManager.initializePEC();
        eventManager.run();
    }
}
