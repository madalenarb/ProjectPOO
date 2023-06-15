import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class TestDataGenerator {
    private static final int MAX_NODES = 1500;
    private static final int MIN_NODES = 1000;
    private static final int MAX_WEIGHT = 10;
    private static final String TESTS_DIRECTORY = "SIM";
    private static final int MAX_TIME = 4000;
    private static final int numberOfTests = 1;

    public static void main(String[] args) {
        int fileIndex = 1;

        for (int i = 0; i < numberOfTests; i++) {
            while (true) {
                String filename = TESTS_DIRECTORY + "/input" + fileIndex + ".txt";

                File file = new File(filename);
                if (!file.exists() || file.length() == 0) {
                    int size = generateRandom(MIN_NODES, MAX_NODES);
                    generateTestFile(filename, size);
                    break;
                }

                fileIndex++;
            }
        }
    }

    private static void generateTestFile(String filename, int size) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writeParameters(writer, size);

            // Generate the matrix directly in this class
            int[][] graph = new int[size][size];
            fillGraph(graph, size);

            writeMatrix(writer, graph);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void fillGraph(int[][] graph, int size) {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    int weight = rand.nextInt(MAX_WEIGHT) + 1;  // The +1 is to avoid zero weights
                    graph[i][j] = weight;
                    graph[j][i] = weight;  // It's a symmetric graph
                }
            }
        }
    }

    private static void writeParameters(PrintWriter writer, int size) {
        Random rand = new Random();
        int nestNode = rand.nextInt(size) + 1;
        double alpha = generateRandomDouble(0, 10.0);
        double beta = generateRandomDouble(0, 10.0);
        double delta = generateRandomDouble(0, 2.0);
        double eta = generateRandomDouble(0, 20.0);
        double rho = generateRandomDouble(0, 100.0);
        double gamma = generateRandomDouble(0, 5.0);
        int antColonySize = generateRandom(20, 2000);
        double finalInstant = generateRandomDouble(300.0, MAX_TIME);

        writer.println(size + " " + nestNode + " " + alpha + " " + beta + " " + delta + " " + eta + " " +
                rho + " " + gamma + " " + antColonySize + " " + finalInstant);
    }

    private static void writeMatrix(PrintWriter writer, int[][] graph) {
        for (int[] row : graph) {
            for (int value : row) {
                writer.print(value + " ");
            }
            writer.println();
        }
    }

    private static int generateRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    private static double generateRandomDouble(double min, double max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextDouble();
    }
}
