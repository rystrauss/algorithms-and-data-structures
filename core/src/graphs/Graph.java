package graphs;

import java.util.*;

/**
 * Implementation of a graph of integers.
 *
 * @author Ryan Strauss
 */
public class Graph {

    private Map<Integer, Set<Edge>> incoming, outgoing;
    private int edgeCount, vertexCount;

    public Graph() {
        incoming = new HashMap<>();
        outgoing = new HashMap<>();
        edgeCount = 0;
        vertexCount = 0;
    }

    /**
     * Adds a vertex to the graph.
     *
     * @param i the vertex to be added
     * @return true iff the vertex was successfully added to the graph and false otherwise
     */
    public boolean addVertex(int i) {
        if (hasVertex(i))
            return false;
        incoming.put(i, new HashSet<>());
        outgoing.put(i, new HashSet<>());
        vertexCount++;
        return true;
    }

    /**
     * Adds an edge between the given vertices with a weight of 1.
     *
     * @param a
     * @param b
     * @return true iff the edge was successfully added to the graph and false otherwise
     */
    public boolean addEdge(int a, int b) {
        if (!outgoing.containsKey(a))
            return false;
        if (hasEdge(a, b) || hasEdge(b, a))
            return false;
        outgoing.get(a).add(new Edge(b, 1.));
        incoming.get(b).add(new Edge(a, 1.));
        edgeCount++;
        return true;
    }

    /**
     * Adds an edge between the given vertices with the given weight.
     *
     * @param a
     * @param b
     * @return true iff the edge was successfully added to the graph and false otherwise
     */
    public boolean addEdge(int a, int b, double weight) {
        if (!outgoing.containsKey(a))
            return false;
        if (hasEdge(a, b) || hasEdge(b, a))
            return false;
        outgoing.get(a).add(new Edge(b, weight));
        incoming.get(b).add(new Edge(a, weight));
        edgeCount++;
        return true;
    }

    /**
     * Determines whether or not the given vertex is in the graph.
     *
     * @param i the vertex to check for
     * @return true iff the vertex is in the graph and false otherwise
     */
    public boolean hasVertex(int i) {
        return incoming.containsKey(i);
    }

    /**
     * Determines whether or not the given edge is in the graph.
     *
     * @param from
     * @param to
     * @return true iff there is an edge connecting the given vertices in the graph.
     */
    public boolean hasEdge(int from, int to) {
        if (outgoing.containsKey(from)) {
            for (Edge e : outgoing.get(from)) {
                if (e.endpoint == to)
                    return true;
            }
        }
        return false;
    }


    /**
     * Returns the in-degree of the provided vertex,
     * or -1 if the provided vertex does not exist.
     *
     * @param i ID of the vertex being checked for in-degree.
     * @return In-degree of the vertex with the provided ID, or -1
     * if a non-existent vertex has been provided.
     */
    public int inDegree(int i) {
        if (!hasVertex(i))
            return -1;
        return incoming.get(i).size();
    }

    /**
     * Returns the out-degree of the provided vertex,
     * or -1 if the provided vertex does not exist.
     *
     * @param i ID of the vertex being checked for out-degree.
     * @return Out-degree of the vertex with the provided ID, or -1
     * if a non-existent vertex has been provided.
     */
    public int outDegree(int i) {
        if (!hasVertex(i))
            return -1;
        return outgoing.get(i).size();
    }

    /**
     * Returns a BFS starting from the vertex provided.
     *
     * @param rootId The vertex from which to start the BFS.
     * @return an ArrayList of the vertices in BFS ordering
     */
    public ArrayList<Integer> bfs(int rootId) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> toVisit = new LinkedList<>();
        ArrayList<Integer> bfs = new ArrayList<>();
        toVisit.add(rootId);
        while (!toVisit.isEmpty()) {
            int value = toVisit.poll();
            if (visited.add(value)) {
                bfs.add(value);
            }
            for (Edge e : outgoing.get(value)) {
                if (!visited.contains(e.endpoint))
                    toVisit.add(e.endpoint);
            }
        }
        return bfs;
    }

    /**
     * Returns a DFS starting from the vertex provided.
     *
     * @param rootId The vertex from which to start the DFS.
     * @return an ArrayList of the vertices in DFS ordering
     */
    public ArrayList<Integer> dfs(int rootId) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> toVisit = new Stack<>();
        ArrayList<Integer> dfs = new ArrayList<>();
        toVisit.add(rootId);
        while (!toVisit.isEmpty()) {
            int value = toVisit.pop();
            if (visited.add(value)) {
                dfs.add(value);
            }
            for (Edge e : outgoing.get(value)) {
                if (!visited.contains(e.endpoint))
                    toVisit.push(e.endpoint);
            }
        }
        return dfs;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return the number of vertices
     */
    public int vertexCount() {
        return vertexCount;
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges
     */
    public int edgeCount() {
        return edgeCount;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return this graph as a string
     */
    public String toString() {
        return String.format("Graph(verticies=%d, edges=%d)", vertexCount, edgeCount);
    }

    private class Edge implements Comparable {

        private int endpoint;
        private double weight;

        private Edge(int endpoint, double weight) {
            this.endpoint = endpoint;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            Edge e = (Edge) o;
            if (weight == e.weight)
                return 0;
            return (weight < e.weight) ? -1 : 1;
        }

    }
}
