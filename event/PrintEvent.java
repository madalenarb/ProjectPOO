package event;

import ant.AntColony;
import main.ParameterReader;

public class PrintEvent implements Event {
    private double eventTime;

    public PrintEvent(double eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public void executeEvent(EventManager PEC) {
        System.out.println("Observation number: ");
        System.out.printf("%-3s : %-20s", "Present instant", getEventTime());
        System.out.printf("%-3s : %-20s", "Number of move events", "TBD");
        System.out.printf("%-3s : %-20s", "Number of evaporation events", "TBD");
        System.out.printf("%-3s : %-20s", "Top candidate cycles", "TBD - função que retorna o top 5");
        System.out.printf("%-3s : %-20s", "Best Hamiltonian cycle", "TBD");
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
