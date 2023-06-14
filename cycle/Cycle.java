package cycle;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Iterator;
import graph.GraphFacade;
import main.ParameterReader;
import main.utils.PrinterController;

import java.util.BitSet;
import event.EventManager;
import event.PheromoneEvaporationEvent;
import java.util.Random;

/**
 * Represents a cycle in the graph with its associated weight.
 * The cycle is represented as a linked list of nodes.
 */
public class Cycle {
	private LinkedList<Integer> cycle;
	int currentCycleWeight;
	
	/**
     * Constructs a new Cycle object with the nest node and initializes the weight to 0.
     */
	public Cycle() {
		// Initialize the cycle with the nest node
        cycle = new LinkedList<>();
        cycle.add(ParameterReader.getNest()); 
        currentCycleWeight = 0; // Initialize the weight to 0
    }
	

	/**
     * Returns an unmodifiable view of the cycle.
     * @return Unmodifiable list representing the cycle.
     */
	public LinkedList<Integer> getCycleList() {
		return cycle;
	}
	

	/**
     * Adds a new node to the cycle.
     * @param nod the node to be added to the cycle.
     */
	public void addNode(int nod) {
		cycle.add(nod); // Add the node to the cycle
    }
	
	/**
	 * Returns the last node in the cycle. If the cycle is empty, returns -1.
	 * @return the last node in the cycle or -1 if the cycle is empty.
     */
	public int getLastNode() {
		// If the cycle is not empty, return the last node
		if(!cycle.isEmpty()) {
			return cycle.getLast();
		}
		// Otherwise, if the cycle is empty, return -1
		return -1;
	}
	
	/**
     * Checks if the provided node is the last one in the cycle.
     * @param element Node to check.
     * @return true if the provided node is the last one, false otherwise.
     */
	public boolean isLastNode(Integer element) {
		// If the element is not null, check if it's the last node
		if(element != null) {
			int el = element.intValue();
			return (getLastNode() == el);
		}
		// If the element is null, return false
		return false;
	}
	
	/**
     * Retrieves the current weight of the cycle.
     * @return Current weight of the cycle.
     */
	public int getCurrentCycleWeight() {
		return currentCycleWeight;
    }
    
	/**
     * Increments the current cycle weight by a given amount.
     * @param increment Amount to increment the cycle weight by.
     */
    public void incrementCurrentCycleWeight(int increment) {
    	currentCycleWeight = currentCycleWeight + increment;
    }
	
	/**
     * Removes nodes from the cycle up to a given stop node, and updates the current cycle weight accordingly.
     * @param stopNod Node at which to stop removing nodes.
     * @param nVNodes BitSet representing nodes in the cycle.
     */
	public void removeCycle(int stopNod, BitSet nVNodes) {
		GraphFacade g = GraphFacade.getInstance();
		// Create an iterator starting at the end of the cycle
		ListIterator<Integer> iterator = cycle.listIterator(cycle.size());
		// Create an iterator starting at the end of the cycle
		int reduceWeight = 0;
		Integer previousElement = null;
		// Loop backwards through the cycle
		while (iterator.hasPrevious()) {
			Integer nextElement = (Integer) iterator.previous();
			// If the previous element is not null
			if(previousElement != null) {
				reduceWeight += g.getWeight(previousElement, nextElement);
			}
			// If the next element is the stop node, break out of the loop
			if(nextElement.equals(stopNod)) {
				break;
			}
			// Set the node as not visited
			nVNodes.set(nextElement, true);
			// Update the previous element
			previousElement = Integer.valueOf(nextElement.intValue());
			// Remove the next element from the cycle
			iterator.remove();
		}
		// Update the current cycle weight
		currentCycleWeight -= reduceWeight;
	}
	
	/**
     * This method lays the pheromone in the cycle based on the current cycle weight.
     * This method also takes care of scheduling a pheromone evaporation event.
     *
     * @param PEC EventManager object that manages the events.
     */
	public void layP(EventManager PEC) {
		GraphFacade gr = GraphFacade.getInstance(); // Get the singleton instance of the graph
		float p = ParameterReader.getGamma()*(gr.getTotalWeight()/currentCycleWeight); // Calculate the pheromone level
		Iterator<Integer> iterator = cycle.iterator(); // Create an iterator for the cycle
		// Initialize the previous and current nodes
		Integer previousNode = null;
		Integer currentNode = null;
		

		// Iterate through the cycle or until the last node is reached
		while (iterator.hasNext() || isLastNode(previousNode)) {
			
			// If there are still nodes in the cycle, get the next node
			if(iterator.hasNext()) {
				currentNode = iterator.next();
			}
			// If no nodes in the cycle, get the nest node.
			else {
				currentNode = ParameterReader.getNest();
			}
			
			// If the previous node is not null, perform the pheromone laying
			if(previousNode != null) {
				double currentPheromones = gr.getPheromones(previousNode, currentNode);
				gr.increaseEdgePheromones(previousNode, currentNode, p);
				
				// If pheromone level is 0 before the increment, schedule a pheromone evaporation event
				if(currentPheromones == 0) {
					Random random = new Random();
        			double next = random.nextDouble();
        			double newEventTime = PEC.getTime() + (-ParameterReader.getEta()*Math.log(1.0-next)); // Get PEC time
					PEC.addEvent(new PheromoneEvaporationEvent(newEventTime, previousNode, currentNode)); // Schedule a pheromone evaporation event
				}
			}
			
			previousNode = currentNode; // Set current node as previous node for the next iteration
		}
	}
	
	/**
     * This method prints all elements of the cycle.
     * It prints the elements as a comma separated string surrounded by braces.
     */
	public void printElements() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		Iterator<Integer> iterator = cycle.iterator();
		while (iterator.hasNext()) {
			Integer current = iterator.next() + 1;
			sb.append(current);
			if(iterator.hasNext()) {
				sb.append(",");
			}
		}
		sb.append("}:" + currentCycleWeight);
		PrinterController.getInstance().println(sb.toString());
	}
}
