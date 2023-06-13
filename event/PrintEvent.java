package event;

import ant.AntColony;
import main.ParameterReader;
import main.utils.ConsoleFilePrinters;

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
        
        ConsoleFilePrinters.getInstance().println("Observation " + printEventNumber + ": ");
        ConsoleFilePrinters.getInstance().printf("%-3s : %-20s\n", "Present instant", getEventTime());
        ConsoleFilePrinters.getInstance().printf("%-3s : %-20s\n", "Number of move events", getPec().getMoveEventCounter());
        ConsoleFilePrinters.getInstance().printf("%-3s : %-20s\n", "Number of evaporation events", getPec().getPheromoneEventCounter());
        ConsoleFilePrinters.getInstance().printf("%-3s : \n", "Top candidate cycles");
        antColony.printTopCycles();
        ConsoleFilePrinters.getInstance().printf("%-3s :\n", "Best Hamiltonian cycle");
        antColony.printBestHamiltonianCycle();
        ConsoleFilePrinters.getInstance().println("");
        
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
