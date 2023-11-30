
import java.util.*;

public class DFS {

    static class Graph {
        private int vertices;
        private LinkedList<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination) {
            adjList[source].add(destination);
            adjList[destination].add(source); // Assuming an undirected graph
        }

        public List<Integer> shortestPath(int start, int end) {
            boolean[] visited = new boolean[vertices];
            int[] parent = new int[vertices];

            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int neighbor : adjList[current]) {
                    if (!visited[neighbor]) {
                        queue.add(neighbor);
                        visited[neighbor] = true;
                        parent[neighbor] = current;

                        if (neighbor == end) {
                            // Reconstruct the path from end to start
                            return constructPath(parent, start, end);
                        }
                    }
                }
            }

            // If no path is found
            return Collections.emptyList();
        }

        private List<Integer> constructPath(int[] parent, int start, int end) {
            List<Integer> path = new ArrayList<>();
            int current = end;
            while (current != start) {
                path.add(current);
                current = parent[current];
            }
            path.add(start);
            Collections.reverse(path);
            return path;
        }
    }

    public static void main(String[] args) {
        int vertices = 7;
        Graph graph = new Graph(vertices);

        // Adding edges to the graph
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);

        int start = 0;
        int end = 6;

        List<Integer> shortestPath = graph.shortestPath(start, end);

        if (!shortestPath.isEmpty()) {
            System.out.println("Shortest Path from " + start + " to " + end + ": " + shortestPath);
        } else {
            System.out.println("No path found from " + start + " to " + end);
        }
    }
}
