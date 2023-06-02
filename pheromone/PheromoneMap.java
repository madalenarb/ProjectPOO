package pheromone;

import java.util.HashMap;
import java.util.Map;
//import java.util.Iterator;

public class PheromoneMap {
	private Map<int[], Edge> pheromoneMap;
	
	public PheromoneMap() {
        pheromoneMap = new HashMap<>();
    }
	
	public void addEdge(int startNode, int endNode, float pheromoneLevel) {
        Edge edge = new Edge(startNode, endNode, pheromoneLevel);
        int[] key = {startNode, endNode};
        pheromoneMap.put(key, edge);
    }
	
	public void removeEdge(int startNode, int endNode) {
        int[] key = {startNode, endNode};
        pheromoneMap.remove(key);
    }
	
	public Edge getEdge(int startNode, int endNode) {
        int[] key = {startNode, endNode};
        return pheromoneMap.get(key);
    }
	
	public float getPheromone(int startNode, int endNode) {
		Edge edge = getEdge(startNode, endNode);
		float ph = 0;
		if(edge != null) {
			ph = edge.getPheromoneLevel();
		}
		return ph;
	}
	
	public void increasePheromoneLevel(int startNode, int endNode, float increasePheromones) {
        Edge edge = getEdge(startNode, endNode);
        if (edge != null) {
        	float newPheromoneLevel = edge.getPheromoneLevel() + increasePheromones;
            edge.setPheromoneLevel(newPheromoneLevel);
        }
        else {
        	edge = new Edge(startNode, endNode, increasePheromones);
        	int[] key = {startNode, endNode};
            pheromoneMap.put(key, edge);
        }
    }
	
	public void reducePheromoneLevel(float decrement, int startNode, int endNode) {
		
		// Add entries to the pheromoneMap
		
		int[] key = {startNode, endNode};
        Edge value = pheromoneMap.get(key);

		float newPheromoneLevel = value.getPheromoneLevel() - decrement;
		
		if(newPheromoneLevel > 0) {
			// Modify the value associated with the key
			value.setPheromoneLevel(newPheromoneLevel);    
		}
		
		// Remove entries that do not have pheromones
		else {
			pheromoneMap.remove(key); // Remove the entry from the pheromoneMap
		}

	}
	
	public void printEdges() {
        for (Map.Entry<int[], Edge> entry : pheromoneMap.entrySet()) {
            int[] key = entry.getKey();
            Edge edge = entry.getValue();
            System.out.println("Start Node: " + key[0] + ", End Node: " + key[1] + ", Pheromone Level: " + edge.getPheromoneLevel());
        }
    }
}
