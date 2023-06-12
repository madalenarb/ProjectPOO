package graph;

import pheromone.PheromoneMap;
import main.ParameterReader;

/**
 * Provides a facade interface to interact with the graph and pheromone map.
 * This class is a singleton.
 *
 * <p>Class Parameters:</p>
 * <ul>
 * <li>{@code pheromoneMap} - a PheromoneMap object used to manage the pheromone levels on the edges of the graph.</li>
 * <li>{@code antGraph} - a GraphGenerator object used to manage the graph that represents the ant colony's environment.</li>
 * <li>{@code facadeInstance} - the singleton instance of the GraphFacade class.</li>
 * </ul>
 */
public class GraphFacade {
	private PheromoneMap pheromoneMap;
	private GraphGenerator antGraph;
	private static GraphFacade facadeInstance;
	
	private GraphFacade() {
        pheromoneMap = new PheromoneMap();
        antGraph = new GraphGenerator(ParameterReader.getN());
	}
	
	
	/** 
	 * Returns the singleton instance of the GraphFacade.
	 * @return The singleton instance of the GraphFacade.
	 */
	public static GraphFacade getInstance() {
        if (facadeInstance == null) {
        	synchronized (GraphFacade.class) {
                if (facadeInstance == null) {
                	facadeInstance = new GraphFacade();
                }
        	}
        }
        return facadeInstance;
    }
	
	/**
	 * Returns the weight of an edge between two nodes.
	 * @param node1 The first node.
	 * @param node2 The second node.
	 * @return The weight of the edge between the nodes.
	 */
	public int getWeight(int node1, int node2) {
		return antGraph.getWeight(node1, node2);
	}
	
	/**
	 * Returns the pheromone level of an edge between two nodes.
	 * @param node1 The first node.
	 * @param node2 The second node.
	 * @return The pheromone level of the edge between the nodes.
	 */
	public double getPheromones(int node1, int node2) {
		return pheromoneMap.getPheromoneLevel(node1, node2);
	}
	
	/**
	 * Prints the ant graph.
	 */
	public void printAntGraph() {
		antGraph.printGraph();
	}
	
	/**
	 * Fills the graph with weights read from a file.
	 * @param weights The weights read from the file.
	 * @param file The line count of the file.
	 */
	public void fillGraphFileMode(String weights[], int file) {
		antGraph.fillGraphFile(weights, file);
	}
	
	/**
	 * Fills the graph with random weights.
	 * @param n The number of nodes in the graph.
	 * @param maxW The maximum weight of an edge.
	 */
	public void fillGraphReadMode(int n, int maxW) {
		antGraph.fillGraphNoFile(n, maxW);
	}
	
	/**
	 * Increases the pheromone level of an edge.
	 * @param node1 The first node.
	 * @param node2 The second node.
	 * @param increment The amount by which to increase the pheromone level.
	 */
	public void increaseEdgePheromones(int node1, int node2, double increment) {
		pheromoneMap.increasePheromoneLevel(node1, node2, increment);
	}
	
	/**
	 * Reduces the pheromone level of an edge.
	 * @param startNode The start node.
	 * @param endNode The end node.
	 * @return True if the operation was successful, false otherwise.
	 */
	public boolean reducePheromones(int startNode, int endNode) {
		return pheromoneMap.reducePheromoneLevel(ParameterReader.getRho(), startNode, endNode);
	}

	/**
	 * Calculates the total weight of the graph.
	 * @return The total weight of the graph.
	 */
	public int getTotalWeight(){
		int totalweight = 0;
		for(int i = 0; i < ParameterReader.getN(); i++){
			for(int j=i+1; j < ParameterReader.getN(); j++){
				totalweight += antGraph.getWeight(i, j);
			}
		}
		return totalweight;
	}
}