package pheromone;

class Edge {
    private int startNode;
    private int endNode;
    private float pheromoneLevel;

    // Constructor
    Edge(int startNode, int endNode, float pheromoneLevel) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.pheromoneLevel = pheromoneLevel;
    }

    // Getters and setters
    int getStartNode() {
        return startNode;
    }

    void setStartNode(int startNode) {
        this.startNode = startNode;
    }

    int getEndNode() {
        return endNode;
    }

    void setEndNode(int endNode) {
        this.endNode = endNode;
    }

    float getPheromoneLevel() {
        return pheromoneLevel;
    }

    void setPheromoneLevel(float pheromoneLevel) {
        this.pheromoneLevel = pheromoneLevel;
    }
}

