import java.util.*;

public class BFS {

    public static List<String> bfsShortestPath(Map<String, List<String>> graph, String start, String end) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Node(start, new ArrayList<>(Collections.singletonList(start))));

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            String current = currentNode.name;
            List<String> path = currentNode.path;

            if (current.equals(end)) {
                return path; // Found the shortest path
            }

            if (!visited.contains(current)) {
                visited.add(current);

                for (String neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(new Node(neighbor, newPath));
                }
            }
        }

        return null; // No path found
    }

    static class Node {
        String name;
        List<String> path;

        Node(String name, List<String> path) {
            this.name = name;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D", "E"));
        graph.put("C", Arrays.asList("A", "F", "G"));
        graph.put("D", Collections.singletonList("B"));
        graph.put("E", Arrays.asList("B", "H"));
        graph.put("F", Collections.singletonList("C"));
        graph.put("G", Collections.singletonList("C"));
        graph.put("H", Collections.singletonList("E"));

        String startNode = "A";
        String endNode = "G";

        List<String> shortestPath = bfsShortestPath(graph, startNode, endNode);
        
        if (shortestPath != null) {
            System.out.println("Shortest path from " + startNode + " to " + endNode + ": " + shortestPath);
        } else {
            System.out.println("No path found.");
        }
    }
}


