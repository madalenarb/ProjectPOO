/**
 * The main class for the Ant Colony Optimization algorithm.
 *
 * <p>This class contains the entry point for running the algorithm and controlling the execution flow.
 * It initializes the necessary components, such as the ant colony and event manager, and runs the simulation.
 *
 * <p><b>Authors:</b>
 * <ul>
 *   <li>Ana Ferreira</li>
 *   <li>Diogo Neves</li>
 *   <li>Madalena Barros</li>
 * </ul>
 */

package main;

import event.EventManager;

import java.io.IOException;

/**
 * The main class for the Ant Colony Optimization algorithm.
 *
 * <p>This class contains the entry point for running the algorithm and controlling the execution flow.
 * It initializes the necessary components, such as the ant colony and event manager, and runs the simulation.
 */
public class Main {
	
    /** 
     * The entry point for running the Ant Colony Optimization algorithm.
    */
    public static void main(String[] args) throws IOException {
		
        ParameterReader.readingMode(args[0]);
        if(ParameterReader.getReadingMode() == 0){
            if(args.length < 12){
                main.utils.MessageError.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 12){
                main.utils.MessageError.CommandNotFound("Too many arguments", args[0]);
            } else {
                ParameterReader.readParameters(args);
            }
        }

        else if(ParameterReader.getReadingMode() == 1){
            if(args.length < 2){
                main.utils.MessageError.CommandNotFound("Too few arguments", args[0]);
            } else if(args.length > 2){
                main.utils.MessageError.CommandNotFound("Too many arguments", args[0]);
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
