package event;

import ant.AntColony;
import main.ParameterReader;

public class PrintEvent implements Event {
    private double eventTime;
    private AntColony antColony;

    public PrintEvent(double eventTime) {
        this.eventTime = eventTime;
        this.antColony = AntColony.getInstance();
    }

    @Override
    public void executeEvent(EventManager PEC) {
    	EventCounter counter = EventCounter.getInstance();
        System.out.println("Observation number: ");
        System.out.printf("%-3s : %-20s\n", "Present instant", getEventTime());
        System.out.printf("%-3s : %-20s\n", "Number of move events", counter.getAntMoveEvents());
        System.out.printf("%-3s : %-20s\n", "Number of evaporation events", counter.getPheromoneEvaporationEvents());
        System.out.printf("%-3s : \n", "Top candidate cycles");
        antColony.printTopCycles();
        System.out.printf("%-3s :\n", "Best Hamiltonian cycle");
        antColony.printBestHamiltonianCycle();
        PEC.addEvent(new PrintEvent(setNextEventTime()));
    }

    @Override
    public double getEventTime() {
        return eventTime;
    }

    @Override
    public double setNextEventTime() {
        return eventTime + ParameterReader.getTau()/20;
    }

    // make top candidate cycles
    // make best hamiltonian cycle
    // make number of move events
    
}
