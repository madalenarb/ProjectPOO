package AntColonyProject;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraph_di {
    protected int[][] adjacencyMatrix;
    protected Map<Integer, Vertex> vertexMap;

    public WeightedGraph_di(int size) {
        adjacencyMatrix = new int[size][size];
        vertexMap = new HashMap<>();
    }

    public void addVertex(Vertex vertex){
        vertexMap.put(vertex.getId(), vertex);
    }

    public void addEdge(Vertex start, Vertex end, int weight){
        int startID = start.getId();
        int endID = end.getId();
        adjacencyMatrix[startID][endID] = weight;
    }


    
}
