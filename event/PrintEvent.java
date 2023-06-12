package event;

import ant.AntColony;
import main.ParameterReader;

/**
 * Represents a print event in the simulation.
 */
public class PrintEvent extends Event {

    /**
     * Constructs a new PrintEvent object.
     *
     * @param time The time at which the event occurs.
     */
    public PrintEvent(double time) {
        super(time);
    }

    /**
     * Executes the print event.
     */
    @Override
    public void executeEvent() {
    	AntColony antColony = AntColony.getInstance();
    	
    	getPec().setTime(getEventTime());
    	getPec().incrementPrintEvent();
    	int printEventNumber = getPec().getPrintEventCounter();
    	
        System.out.println("Observation " + printEventNumber + ": ");
        System.out.printf("%-3s : %-20s\n", "Present instant", getEventTime());
        System.out.printf("%-3s : %-20s\n", "Number of move events", getPec().getMoveEventCounter());
        System.out.printf("%-3s : %-20s\n", "Number of evaporation events", getPec().getPheromoneEventCounter());
        System.out.printf("%-3s : \n", "Top candidate cycles");
        antColony.printTopCycles();
        System.out.printf("%-3s :\n", "Best Hamiltonian cycle");
        antColony.printBestHamiltonianCycle();
        System.out.println();
        
        getPec().addEvent(new PrintEvent(setNextEventTime()));
    }

    
    /**
     * Sets the time for the next print event.
     *
     * @return The time for the next event.
     */
    @Override
    public double setNextEventTime() {
        return getEventTime() + ParameterReader.getTau()/20;
    }
    
}
