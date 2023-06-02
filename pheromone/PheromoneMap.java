package pheromone;

import java.util.HashMap;
import java.util.Map;
//import java.util.Iterator;

public class PheromoneMap {
	private Map<int[], Double> pheromoneMap;
	
	public PheromoneMap() {
        pheromoneMap = new HashMap<>();
    }

	public int[] organizeNodes(int startNode, int endNode) {
		int[] key = {startNode, endNode};
		key[0] = Math.min(startNode, endNode);
		key[1] = Math.max(startNode, endNode);
		return key;
	}
	
	public void addPheromone(int startNode, int endNode, double pheromoneLevel) {
		int[] key = organizeNodes(startNode, endNode);
        pheromoneMap.put(key, pheromoneLevel);
    }
	
	public void removePheromone(int startNode, int endNode) {
        int[] key = organizeNodes(startNode, endNode);
        pheromoneMap.remove(key);
    }

	public double getPheromoneLevel(int startNode, int endNode) {
		int[] key = organizeNodes(startNode, endNode);
		return getPheromone(key);
	}
	
	public double getPheromone(int[] key) {
        Double ph = pheromoneMap.get(key);
		if(ph == null){
			return 0;
		}
		return ph;
    }
	
	public void increasePheromoneLevel(int startNode, int endNode, double increasePheromones) {
		int[] key = organizeNodes(startNode, endNode);
        double ph = getPheromone(key);
		if(ph == 0){
			pheromoneMap.put(key, increasePheromones);
		} else {
			pheromoneMap.replace(key, ph+increasePheromones);
		}
    }
	
	public void reducePheromoneLevel(double decrement, int startNode, int endNode) {
		
		// Add entries to the pheromoneMap
		
		int[] key = organizeNodes(startNode, endNode);
        double value = getPheromone(key);

		double newPheromoneLevel = value - decrement;
		
		if(newPheromoneLevel > 0) {
			// Modify the value associated with the key
			pheromoneMap.replace(key, newPheromoneLevel);
		}
		
		// Remove entries that do not have pheromones
		else {
			pheromoneMap.remove(key); // Remove the entry from the pheromoneMap
		}

	}
	
	public void printEdges() {
        for (Map.Entry<int[], Double> entry : pheromoneMap.entrySet()) {
            int[] key = entry.getKey();
            double value = entry.getValue();
            System.out.println("Start Node: " + key[0] + ", End Node: " + key[1] + ", Pheromone Level: " + value);
        }
    }
}
