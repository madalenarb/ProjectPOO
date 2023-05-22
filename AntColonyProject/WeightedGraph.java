package AntColonyProject;

import java.util.Arrays;

public class WeightedGraph {
    protected int[][] adjacencyMatrix;
    int size;

    public WeightedGraph(int[][] adjacencyMatrix, int size) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.size = size;
    }

    public void buildWeightedGraph(int i, int j, int weight) {
        adjacencyMatrix[i][j] = weight;
    }

    public void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        for(int i = 0; i < adjacencyMatrix.length; i++) {
            for(int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getWeight(int i, int j) {
        return adjacencyMatrix[i][j];
    }


    
}
