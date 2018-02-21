import java.util.*;
import java.util.LinkedList;

public class Graph {

    private class Edge implements Comparable {
        public int endpoint;
        public double weight;

        public Edge(int endpoint, double weight) {
            this.endpoint = endpoint;
            this.weight = weight;
        }

        public int compareTo(Object o) {
            Edge e = (Edge)o;
            if (weight == e.weight)
                return 0;
            return (weight < e.weight) ? -1 : 1;
        }

    }

    private Map<Integer, List<Edge>> outgoing, incoming;
    private int V, E;

    public Graph() {
        outgoing = new HashMap<>();
        incoming = new HashMap<>();
        V = 0;
        E = 0;
    }

    /**
     * Add a vertex to the graph.
     * @param id ID of the vertex.
     * @return <code>true</code> if and only if the vertex with this ID was
     * not present in the graph before.
     */
    public boolean addVertex(int id) {
        if (!outgoing.containsKey(id)) {
            outgoing.put(id, new ArrayList<>());
            incoming.put(id, new ArrayList<>());
            V++;
            return true;
        }
        return false;
    }

    /**
     * Returns true if the vertex is present in the graph.
     * @param vertexId ID of the vertex being checked.
     * @return <code>true</code> if the vertex with the provided ID is in the graph.
     */
    public boolean hasVertex(int vertexId) {
        return outgoing.containsKey(vertexId);
    }

    /**
     * Adds an edge to the graph.
     * @param source ID of the source vertex.
     * @param target ID of the target vertex.
     * @return <code>true</code> if and only if both source and target
     * vertices exist in the graph, and an edge from
     * source to target did not exist before.
     */
    public boolean addEdge(int source, int target, double weight) {
        if (!hasVertex(source))
            return false;

        if (!hasVertex(target))
            return false;

        for (Edge e : outgoing.get(source)) {
            if (e.endpoint == target)
                return false;
        }
        outgoing.get(source).add(new Edge(target, weight));
        incoming.get(target).add(new Edge(source, weight));
        E++;
        return true;
    }

    /**
     * Returns the in-degree of the provided vertex,
     * or -1 if the provided vertex does not exist.
     * @param vertexId ID of the vertex being checked for in-degree.
     * @return In-degree of the vertex with the provided ID, or -1
     * if a non-existent vertex has been provided.
     */
    public int inDegree(int vertexId) {
        if (!hasVertex(vertexId)) {
            return -1;
        }

        return incoming.get(vertexId).size();
    }

    /**
     * Returns the out-degree of the provided vertex,
     * or -1 if the provided vertex does not exist.
     * @param vertexId ID of the vertex being checked for out-degree.
     * @return Out-degree of the vertex with the provided ID, or -1
     * if a non-existent vertex has been provided.
     */
    public int outDegree(int vertexId) {
        if (!hasVertex(vertexId)) {
            return -1;
        }

        return outgoing.get(vertexId).size();
    }

    /**
     * Prints a BFS starting from the vertex provided.
     * @param rootId The vertex from which to start the BFS.
     */
    public void bfs(int rootId) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new LinkedList<>();

        toVisit.add(rootId);

        while (!toVisit.isEmpty()) {
            int value = toVisit.poll();
            if (visited.add(value)) {
                System.out.println(value);
            }
            for (Edge e : outgoing.get(value)) {
                if (!visited.contains(e.endpoint))
                    toVisit.add(e.endpoint);
            }
        }
    }

    /**
     * Prints a DFS starting from the vertex provided.
     * @param rootId The vertex from which to start the DFS.
     */
    public void dfs(int rootId) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> toVisit = new Stack<>();

        toVisit.add(rootId);

        while (!toVisit.isEmpty()) {
            int value = toVisit.pop();
            if (visited.add(value)) {
                System.out.println(value);
            }
            for (Edge e : outgoing.get(value)) {
                if (!visited.contains(e.endpoint))
                    toVisit.push(e.endpoint);
            }
        }
    }

}
