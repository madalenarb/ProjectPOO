/**
 * Contains classes related to ants in the ant colony optimization algorithm.
 * The package includes the following classes:
 *
 * <ul>
 * <li>{@link ant.Ant}: Represents an ant in the ant colony optimization algorithm.
 *     <ul>
 *     <li>{@link ant.Ant#getCurrentCycle()}: Gets the current cycle of the ant.</li>
 *     <li>{@link ant.Ant#setCurrentCycle(cycle.Cycle)}: Sets the current cycle of the ant.</li>
 *     <li>{@link ant.Ant#layPheromones(event.EventManager)}: Lays pheromones on the current cycle.</li>
 *     <li>{@link ant.Ant#resetNonVisitedNodes()}: Resets the non-visited nodes for the ant.</li>
 *     <li>{@link ant.Ant#getMeanEdgeTime()}: Gets the mean edge time.</li>
 *     <li>{@link ant.Ant#setMeanEdgeTime(int)}: Sets the mean edge time based on the weight.</li>
 *     <li>{@link ant.Ant#AreThereUnvisitedNodes()}: Checks if there are any unvisited nodes.</li>
 *     <li>{@link ant.Ant#handleVisitedNodes(java.util.ArrayList)}: Handles the visited nodes and determines if the cycle is completed.</li>
 *     <li>{@link ant.Ant#getNodes(graph.GraphFacade, int, java.util.ArrayList, java.util.Map)}: Gets the neighboring nodes, calculates their probabilities, and returns the sum of probabilities.</li>
 *     <li>{@link ant.Ant#calculateProbabilities(graph.GraphFacade, int, int, java.util.Map, double)}: Calculates the probabilities of neighboring nodes and stores them in the probabilityList.</li>
 *     <li>{@link ant.Ant#InverseTranformSampling(graph.GraphFacade, int, java.util.Map, double)}: Performs inverse transform sampling to choose the next node based on the probabilities.</li>
 *     <li>{@link ant.Ant#chooseNextNode()}: Chooses the next node to visit.</li>
 *     </ul>
 * </li>
 * <li>{@link ant.AntColony}: Represents an Ant Colony used in the ant colony optimization algorithm.
 *     <ul>
 *     <li>{@link ant.AntColony#getInstance()}: Gets the singleton instance of the AntColony.</li>
 *     <li>{@link ant.AntColony#printBestHamiltonianCycle()}: Prints the best Hamiltonian cycle found by the ants.</li>
 *     <li>{@link ant.AntColony#printTopCycles()}: Prints the top cycles found by the ants.</li>
 *     <li>{@link ant.AntColony#addCycleToPQ(cycle.Cycle)}: Adds a cycle to the priority queue of cycles.</li>
 *     <li>{@link ant.AntColony#moveAnt(int)}: Moves an ant to the next node and checks if it completed a cycle.</li>
 *     <li>{@link ant.AntColony#restartPath(int)}: Restarts the path for an ant.</li>
 *     <li>{@link ant.AntColony#antLaysPheromones(int, event.EventManager)}: Ant lays pheromones on the current cycle.</li>
 *     <li>{@link ant.AntColony#meanTraverseTime(int)}: Gets the mean traverse time for an ant.</li>
 *     </ul>
 * </li>
 * </ul>
 *
 * These classes work together to simulate the behavior of ants in the ant colony optimization algorithm.
 * The ants make decisions about which nodes to visit, lay pheromones, and manage their current cycles.
 * The AntColony manages the ants, cycles, and the process of laying pheromones.
 */
package ant;
