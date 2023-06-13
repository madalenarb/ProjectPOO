/**
 * Contains classes related to ants in the ant colony optimization algorithm.
 * The package includes the following classes:
 *
 * <ul>
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
