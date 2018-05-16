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
        outgoing.get(b).add(new Edge(a, weight));
        incoming.get(a).add(new Edge(b, weight));
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
     * Finds the shortest path from the source vertex to all other vertices.
     * <p>
     * Implements Dijkstra's algorithm using a priority queue.
     *
     * @param source the vertix from which to calculate distances
     * @return a map where keys are vertices and values are distances
     */
    public Map<Integer, Double> shortestPath(int source) {
        if (!incoming.keySet().contains(source))
            return null;
        Map<Integer, Double> dist = new HashMap<>();
        PriorityQueue<LabeledVertex> Q = new PriorityQueue<>();
        for (int i : incoming.keySet()) {
            if (i == source)
                dist.put(i, 0.0);
            else
                dist.put(i, Double.MAX_VALUE);
        }
        Q.add(new LabeledVertex(source, dist.get(source)));
        while (!Q.isEmpty()) {
            LabeledVertex u = Q.poll();
            for (Edge e : outgoing.get(u.id)) {
                if (dist.get(e.endpoint) > (dist.get(u.id) + e.weight)) {
                    dist.put(e.endpoint, dist.get(u.id) + e.weight);
                    Q.add(new LabeledVertex(e.endpoint, dist.get(u.id) + e.weight));
                }
            }
        }
        return dist;
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

    private class LabeledVertex implements Comparable<LabeledVertex> {

        int id;
        double distance;

        private LabeledVertex(int id, double distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(LabeledVertex o) {
            return (int) (this.distance - o.distance);
        }
    }

    private class Edge implements Comparable<Edge> {

        private int endpoint;
        private double weight;

        private Edge(int endpoint, double weight) {
            this.endpoint = endpoint;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            if (weight == e.weight)
                return 0;
            return (weight < e.weight) ? -1 : 1;
        }

    }
}
