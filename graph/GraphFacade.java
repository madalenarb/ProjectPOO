package graph;

import pheromone.PheromoneMap;
import main.ParameterReader;

public class GraphFacade {
	private static GraphFacade instance;
	private PheromoneMap pheromoneMap;
	private GraphGenerator antGraph;
	
	public GraphFacade(int size) {
        antGraph = new GraphGenerator(size);
        pheromoneMap = new PheromoneMap();
	}
	
	public static GraphFacade getInstance() {
		if (instance == null) {
			synchronized (GraphFacade.class) {
				if (instance == null) {
					instance = new GraphFacade(ParameterReader.getN());
				}
			}
		}
		return instance;
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
	
	public boolean reducePheromones(int startNode, int endNode) {
		return pheromoneMap.reducePheromoneLevel(ParameterReader.getRho(), startNode, endNode);
	}

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