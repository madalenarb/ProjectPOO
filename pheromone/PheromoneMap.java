package pheromone;

import java.util.HashMap;
import java.util.Map;
//import java.util.Iterator;

/**
 * Represents a map of pheromones laid on the graph edges.
 * 
 * <p>Class Parameters:</p>
 * <ul>
 * <li>{@code pheromoneMap} - a map representing the edges of the graph and their associated pheromone levels.</li>
 * </ul>
 */
public class PheromoneMap {
	private Map<int[], Double> pheromoneMap;
	
	/**
     * Constructs a new PheromoneMap.
     */
	public PheromoneMap() {
        pheromoneMap = new HashMap<>();
    }

	
	/**
     * Organizes two nodes in ascending order.
     *
     * @param startNode The first node.
     * @param endNode   The second node.
     * @return An array containing the nodes in ascending order.
     */
	public int[] organizeNodes(int startNode, int endNode) {
		int[] key = {startNode, endNode};
		key[0] = Math.min(startNode, endNode);
		key[1] = Math.max(startNode, endNode);
		return key;
	}
	
	/**
     * Adds pheromones to the edge between two nodes in the graph.
     *
     * @param startNode      The first node.
     * @param endNode        The second node.
     * @param pheromoneLevel The level of pheromones to add.
     */
	public void addPheromone(int startNode, int endNode, double pheromoneLevel) {
		int[] key = organizeNodes(startNode, endNode);
        pheromoneMap.put(key, pheromoneLevel);
    }
	
	/**
	 * Removes pheromones from the edge between two nodes in the graph.
	 *
	 * @param startNode The first node.
	 * @param endNode   The second node.
	 */
	public void removePheromone(int startNode, int endNode) {
        int[] key = organizeNodes(startNode, endNode);
        pheromoneMap.remove(key);
    }

	/**
     * Gets the level of pheromones on the edge between two nodes in the graph.
     *
     * @param startNode The first node.
     * @param endNode   The second node.
     * @return The level of pheromones on the edge.
     */
	public double getPheromoneLevel(int startNode, int endNode) {
		int[] key = organizeNodes(startNode, endNode);
		return getPheromone(key);
	}
	
	/**
     * Gets the level of pheromones on an edge given its key.
     *
     * @param key The key representing the edge.
     * @return The level of pheromones on the edge.
     */
	public double getPheromone(int[] key) {
        Double ph = pheromoneMap.get(key);
		if(ph == null){
			return 0;
		}
		return ph;
    }
	
	/**
     * Increases the level of pheromones on the edge between two nodes in the graph.
     *
     * @param startNode          The first node.
     * @param endNode            The second node.
     * @param increasePheromones The amount of pheromones to increase.
     */
	public void increasePheromoneLevel(int startNode, int endNode, double increasePheromones) {
		int[] key = organizeNodes(startNode, endNode);
        double ph = getPheromone(key);
		if(ph == 0){
			pheromoneMap.put(key, increasePheromones);
		} else {
			pheromoneMap.replace(key, ph+increasePheromones);
		}
    }
	
	/**
     * Reduces the level of pheromones on the edge between two nodes in the graph.
     *
     * @param decrement The amount of pheromones to decrease.
     * @param startNode The first node.
     * @param endNode   The second node.
     * @return True if there are still pheromones left after reduction, False otherwise.
     */
	public boolean reducePheromoneLevel(double decrement, int startNode, int endNode) {
		
		// Add entries to the pheromoneMap
		
		int[] key = organizeNodes(startNode, endNode);
        double value = getPheromone(key);

		double newPheromoneLevel = value - decrement;
		
		if(newPheromoneLevel > 0) {
			// Modify the value associated with the key
			pheromoneMap.replace(key, newPheromoneLevel);
			return true;
		}
		
		// Remove entries that do not have pheromones
		else {
			pheromoneMap.remove(key); // Remove the entry from the pheromoneMap
			return false;
		}

	}
	
	/**
     * Prints all edges in the graph and their associated pheromone levels.
     */
	public void printEdges() {
        for (Map.Entry<int[], Double> entry : pheromoneMap.entrySet()) {
            int[] key = entry.getKey();
            double value = entry.getValue();
            System.out.println("Start Node: " + key[0] + ", End Node: " + key[1] + ", Pheromone Level: " + value);
        }
    }
}