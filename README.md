# Ant Colony Optimization Simulator
## Description
This project is a simulation of the Ant Colony Optimization algorithm. It uses the principles of the behavior of ants searching for food to find optimized solutions to graph problems. It's written in Java and includes classes to represent the ants, the graph on which they move, and the pheromones they drop.

## Usage
Compile the code with the Makefile. Run the program with the following command:
```bash
make
```
After compiling, the program can be invoked from the command line in two different ways.
### First way:
The following command does not contain a graph. Therefore, a random graph with a Hamiltonian cycle must be generated, with the specified number of nodes n and a maximum edge weight a. The minimum edge weight should be zero. Keep in mind that although the generator must ensure a Hamiltonian cycle, the resulting graph may also contain other cycles. The input parameters (n, n1, α, β, δ, η, ρ, γ, ν, and τ ), alongside the generated graph, should be used to run the simulation.

```bash
java -jar project.jar -r n a n1 α β δ η ρ γ ν τ
```

### Second way:
The following command allows you to provide the graph from a file.
```bash
java -jar project.jar -f <file>
```

## Parameters
<ul>
  <li>n: number of nodes in the graph</li>
  <li>a: maximum edge weight</li>
  <li>n1: number of ants</li>
  <li>α: pheromone importance</li>
  <li>β: heuristic importance</li>
  <li>δ: pheromone evaporation rate</li>
  <li>η: pheromone deposit rate</li>
  <li>ρ: pheromone deposit rate for the best ant</li>
  <li>γ: pheromone deposit rate for the worst ant</li>
  <li>ν: pheromone deposit rate for the best ant in the last iteration</li>
  <li>τ: pheromone deposit rate for the worst ant in the last iteration</li>
</ul>

# Makefile Commands
After compiling, you can also run the Makefile commands.

To compile Java source files into class files:
```bash
make
```

To clean up the project:
```bash
make clean
```

To generate Javadoc:
```bash
make javadoc
```

To open the Javadoc in a web browser:
On Ubuntu:
```bash
make openjavadocUbuntu
```
On Windows:
```bash
make openjavadocWindows
```
On MacOs:
```bash
make openjavadocMac
```

# Test Files
The tests are in the SIM folder. 
To run all the tests:
```bash
make runTestFiles
```

## SIM Files
In this folder, there are files with the following format:
1. **input1.txt**: Simple 5-node graph
2. **input2.txt**: Bigger graph with 100 nodes and weighted edges.
3. **input3.txt**: 5-node graph with negative weights.
4. **input4.txt**: 5-node graph with negative weights and has negative parameters.
5. **input5.txt**: Graph with 5 nodes and that is not symmetric.
6. **input6.txt**: 6-node sparce graph with only a Hamiltonian cycle.
7. **input7.txt**: 6-node dense graph.
8. **input8.txt**: Irregular Graph.
9. **input9.txt**: 8-node simple graph.
10. **input10.txt**: 8-node sparse graph.
11. **input11.txt**: 2377-node graph (Large graph).
12. **input12.txt**: 2377-node graph (Large graph) with less time and less ants.
13. **input13.txt**: 6-node graph filled with 0s - disconnected graph.
14. **input14.txt**: 6-node graph filled with 2s.
15. **input15.txt**: 6-node graph with a diagonal of 0s.
16. **input16.txt**: 1254-node graph (Large graph).
17. **input17.txt**: 500-node graph, mostly with 0s.
18. **input18.txt**: 8-node graph with weighted edges for the most part.
19. **input19.txt**: 2-node graph.
20. **input20.txt**: 1136-node graph (Large graph).
21. **input21.txt**: File only with inputs and no graph.
22. **input22.txt**: 4-node graph with letters in graph.
23. **input23.txt**: 11-node graph.
