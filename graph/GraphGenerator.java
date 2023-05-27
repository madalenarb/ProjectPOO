package graph;

import java.util.Arrays;
import java.util.Random;

public class GraphGenerator {
	
	protected int[][] graph; // Graph
	
    public GraphGenerator(int numVertices) {
    	graph = new int[numVertices][numVertices];
    }
    
    public void setWeight(int i, int j, int w) {
    	graph[i][j] = w;
    }
    
    public int getWeight(int i, int j) {
    	return graph[i][j];
    }
    
    private static void shufflePerm(int[] perm) {
    	Random random = new Random();
        for (int i = perm.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = perm[i];
            perm[i] = perm[j];
            perm[j] = temp;
        }
    }
    
    private static int getRandomWeight(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    public void fillGraphNoFile(int n, int maxW) {
    	
    	int[] permutation = new int[n];
    	int num = 0;
    	
    	for (int[] row : graph) {
            Arrays.fill(row, 0);
            permutation[num] = num;
            ++num;
        }
    	
    	// Generate random permutation of numbers 0 to n-1
        shufflePerm(permutation);
    	
        // Create edges between consecutive nodes in the permutation
        int prevNode = permutation[n - 1];
        for (int currNode : permutation) {
            int weight = getRandomWeight(1, maxW);
            this.setWeight(prevNode, currNode, weight);
            this.setWeight(currNode, prevNode, weight);
            //graph[prevNode][currNode] = weight;
            //graph[currNode][prevNode] = weight;
            prevNode = currNode;
        }
        
    }
    
    public void fillGraphFile(String w[], int linecnt) {
    	for(int i = 0; i < graph[linecnt-1].length; i++){
    		this.setWeight(linecnt - 1, i, Integer.parseInt(w[i]));
            //graph[linecnt-1][i] = Integer.parseInt(w[i]);
        }
    }
    
    public void printGraph() {
    	for(int[] row : graph) {
    		System.out.print("\t\t\t");
    		for(int cell : row) {
    			System.out.print(cell + " ");
    		}
    		System.out.println();
    	}
    }
    
}

