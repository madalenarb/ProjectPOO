package event;

import ant.AntColony;
import main.ParameterReader;

public class PrintEvent extends Event {
    private AntColony antColony;

    public PrintEvent(double time) {
        super(time);
        this.antColony = AntColony.getInstance();
    }

    @Override
    public void executeEvent() {
        System.out.println("Observation number: ");
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

    @Override
    public double setNextEventTime() {
        return getPec().getTime() + ParameterReader.getTau()/20;
    }

    // make top candidate cycles
    // make best hamiltonian cycle
    // make number of move events
    
}
