package graph;

import pheromone.PheromoneMap;
import main.ParameterReader;

public class GraphFacade {
	private PheromoneMap pheromoneMap;
	private GraphGenerator antGraph;
	
	public GraphFacade(int size) {
        antGraph = new GraphGenerator(size);
        pheromoneMap = new PheromoneMap();
	}
	
	public int getWeight(int node1, int node2) {
		return antGraph.getWeight(node1, node2);
	}
	
	public double getPheromones(int node1, int node2) {
		return pheromoneMap.getPheromoneLevel(node1, node2);
	}
	
	public void printAntGraph() {
		antGraph.printGraph();
	}
	
	public void fillGraphFileMode(String weights[], int file) {
		antGraph.fillGraphFile(weights, file);
	}
	
	public void fillGraphReadMode(int n, int maxW) {
		antGraph.fillGraphNoFile(n, maxW);
	}
	
	public void increaseEdgePheromones(int node1, int node2, double increment) {
		pheromoneMap.increasePheromoneLevel(node1, node2, increment);
	}
	
	public void reducePheromones(int startNode, int endNode) {
		pheromoneMap.reducePheromoneLevel(ParameterReader.getRho(), startNode, endNode);
	}

	//create a getTotalWeight method
}
