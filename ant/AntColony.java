package ant;
import main.ParameterReader;
import main.utils.ConsoleFilePrinters;
import cycle.Cycle;
import event.EventManager;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Represents an Ant Colony used in the ant colony optimization algorithm.
 */
public class AntColony {
	private static AntColony instance;
	private Ant[] ants;
	private TreeSet<Cycle> hamiltonianCycleQueue;
	private Cycle best;
	
	/**
	 * Private constructor for the AntColony class.
	 */
	private AntColony() {
		int n = ParameterReader.getNu();
		ants = new Ant[n];
		for(int i = 0; i < n; i++) {
			ants[i] = new Ant();
		}
		hamiltonianCycleQueue = new TreeSet<>(Comparator.comparingInt(Cycle::getCurrentCycleWeight).thenComparing(Comparator.comparing(Cycle::hashCode)));
		best = null;
	}
	
	
	/**
	 * Gets the singleton instance of the AntColony.
	 *
	 * @return The singleton instance of the AntColony.
	 */
	public static AntColony getInstance() {
        if (instance == null) {
        	synchronized (AntColony.class) {
                if (instance == null) {
                    instance = new AntColony();
                }
        	}
        }
        return instance;
    }
	
	/**
	 * Prints the best Hamiltonian cycle found by the ants.
	 */
	public void printBestHamiltonianCycle() {
		ConsoleFilePrinters.getInstance().printf("\t\t\t");
		try{
			best.printElements();
		} catch (Exception e) {
			ConsoleFilePrinters.getInstance().println("No Hamiltonian cycle found.");
		}
	}
	
	/**
	 * Prints the top cycles found by the ants.
	 */
	public void printTopCycles() {
		int count = 0;
		for (Cycle c : hamiltonianCycleQueue) {
			if (count >= 5) break; // only print the top 5 cycles
	
			ConsoleFilePrinters.getInstance().printf("\t\t\t");
			c.printElements();
			ConsoleFilePrinters.getInstance().println("");
			count++;
		}
	}
	
	/**
	 * Adds a cycle to the priority queue of cycles.
	 *
	 * @param c The cycle to be added to the priority queue.
	 */
	public void addCycleToPQ(Cycle c){
		
		if(c.getCycleList().equals(best.getCycleList())) {
			return;
		}
		
		for (Cycle element : hamiltonianCycleQueue) {
			if(c.getCycleList().equals(element.getCycleList())) {
				return;
			}
        }
		
		hamiltonianCycleQueue.add(c);
		if(hamiltonianCycleQueue.size() > 5) {
			hamiltonianCycleQueue.pollLast();
		}
	}
	
	/**
	 * Moves an ant to the next node and checks if it completed a cycle.
	 *
	 * @param i The index of the ant to move.
	 * @return True if the ant completed a cycle, false otherwise.
	 */
	public boolean moveAnt(int i) {
		boolean completeCycle = ants[i].chooseNextNode();
		if(completeCycle) { // the ant completed a cycle
			// Compare the cycle with the best cycle
			if(best == null) {
				best = ants[i].getCurrentCycle();
			}
			else {
				if(best.getCurrentCycleWeight() > ants[i].getCurrentCycle().getCurrentCycleWeight()) {
					best = ants[i].getCurrentCycle();
				}
				else {
					// Compare the cycle with the top 5 cycles
					addCycleToPQ(ants[i].getCurrentCycle());
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Restarts the path for an ant.
	 *
	 * @param i The index of the ant.
	 */
	public void restartPath(int i) {
		ants[i].setCurrentCycle(new Cycle());
		ants[i].resetNonVisitedNodes();
	}
	
	/**
	 * Ant lays pheromones on the current cycle.
	 *
	 * @param i   The index of the ant.
	 * @param PEC The event manager for handling events.
	 */
	public void antLaysPheromones(int i, EventManager PEC) {
		ants[i].layPheromones(PEC);
	}
	
	/**
	 * Gets the mean traverse time for an ant.
	 *
	 * @param i The index of the ant.
	 * @return The mean traverse time.
	 */
	public double meanTraverseTime(int i) {
		return ants[i].getMeanEdgeTime();
	}
	
}
