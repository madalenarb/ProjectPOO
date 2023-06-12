/**
 * Contains classes related to the graph in the ant colony optimization algorithm.
 * The package includes the following classes:
 *
 * <ul>
 * <li>{@link graph.GraphFacade}: A facade class that provides a simplified interface to interact with the graph and pheromone map.
 *     <ul>
 *     <li>{@link graph.GraphFacade#getInstance()}: Gets the singleton instance of the GraphFacade.</li>
 *     <li>{@link graph.GraphFacade#getWeight(int, int)}: Gets the weight of an edge between two nodes.</li>
 *     <li>{@link graph.GraphFacade#getPheromones(int, int)}: Gets the pheromone level of an edge between two nodes.</li>
 *     <li>{@link graph.GraphFacade#printAntGraph()}: Prints the graph representing the ant colony.</li>
 *     <li>{@link graph.GraphFacade#fillGraphFileMode(String[], int)}: Fills the graph with weights from a file.</li>
 *     <li>{@link graph.GraphFacade#fillGraphReadMode(int, int)}: Fills the graph with randomly generated weights.</li>
 *     <li>{@link graph.GraphFacade#increaseEdgePheromones(int, int, double)}: Increases the pheromone level of an edge.</li>
 *     <li>{@link graph.GraphFacade#reducePheromones(int, int)}: Reduces the pheromone level of an edge.</li>
 *     <li>{@link graph.GraphFacade#getTotalWeight()}: Calculates the total weight of the graph.</li>
 *     </ul>
 * </li>
 * <li>{@link graph.GraphGenerator}: A class responsible for generating and managing the graph.
 *     <ul>
 *     <li>{@link graph.GraphGenerator#GraphGenerator(int)}: Constructs a GraphGenerator with a given number of vertices.</li>
 *     <li>{@link graph.GraphGenerator#getWeight(int, int)}: Gets the weight of an edge between two nodes.</li>
 *     <li>{@link graph.GraphGenerator#fillGraphNoFile(int, int)}: Fills the graph with randomly generated weights without using a file.</li>
 *     <li>{@link graph.GraphGenerator#fillGraphFile(String[], int)}: Fills the graph with weights from a file.</li>
 *     <li>{@link graph.GraphGenerator#printGraph()}: Prints the graph.</li>
 *     </ul>
 * </li>
 * </ul>
 *
 * These classes work together to represent the graph used in the ant colony optimization algorithm.
 * The GraphFacade provides a higher-level interface to interact with the graph and the pheromone map.
 * The GraphGenerator is responsible for generating the graph and managing its weights.
 * The classes in this package facilitate graph operations and calculations necessary for the optimization algorithm.
 */
package graph;
