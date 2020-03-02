package com.rystrauss.graph;

import java.util.*;

/**
 * Abstract base class for all graphs.
 *
 * @param <V> the type of data stored in the vertices of this graph
 * @author Ryan Strauss
 */
public abstract class AbstractGraph<V> implements Graph<V> {

    Map<V, Set<Edge>> incoming, outgoing;

    /**
     * Constructs an empty graph.
     */
    public AbstractGraph() {
        this.incoming = new HashMap<>();
        this.outgoing = new HashMap<>();
    }

    @Override
    public boolean addVertex(V v) {
        if (incoming.containsKey(v))
            return false;

        incoming.put(v, new HashSet<>());
        outgoing.put(v, new HashSet<>());
        return true;
    }

    @Override
    public Edge getEdge(V source, V target) {
        if (!(incoming.containsKey(source) && incoming.containsKey(target)))
            return null;

        Set<Edge> edges = outgoing.get(source);
        if (edges == null)
            return null;

        for (Edge e : edges) {
            if (e.getTarget() == target)
                return e;
        }

        return null;
    }

    @Override
    public boolean containsEdge(V source, V target) {
        return getEdge(source, target) != null;
    }

    @Override
    public boolean containsEdge(Edge edge) {
        return containsEdge((V) edge.getSource(), (V) edge.getTarget());
    }

    @Override
    public boolean containsVertex(V v) {
        return incoming.containsKey(v);
    }

    @Override
    public Set<Edge> edgeSet() {
        Set<Edge> allEdges = new HashSet<>();

        for (Set<Edge> s : incoming.values())
            allEdges.addAll(s);

        return allEdges;
    }

    @Override
    public Iterator<V> iterator() {
        return incoming.keySet().iterator();
    }

    @Override
    public int size() {
        return incoming.size();
    }

    @Override
    public Map<V, Double> shortestPaths(V source) throws IllegalArgumentException {
        if (!incoming.containsKey(source))
            throw new IllegalArgumentException("The source vertex must exist in the graph.");

        Map<V, Double> dist = new HashMap<>();
        PriorityQueue<LabeledVertex> q = new PriorityQueue<>();

        dist.put(source, 0.0);

        for (V v : this) {
            if (v != source)
                dist.put(v, Double.MAX_VALUE);

            q.add(new LabeledVertex(v, dist.get(v)));
        }

        while (!q.isEmpty()) {
            LabeledVertex u = q.poll();
            Set<Edge> neighbors = outgoing.get(u.data);
            if (neighbors == null)
                continue;

            for (Edge e : neighbors) {
                V v = (V) e.getTarget();
                double alt = dist.get(u.data) + e.getWeight();
                if (alt < dist.get(v)) {
                    dist.put(v, alt);
                    LabeledVertex updatedVertex = new LabeledVertex(v, alt);
                    q.remove(updatedVertex);
                    q.add(updatedVertex);
                }
            }
        }

        return dist;
    }

    private final class LabeledVertex implements Comparable<LabeledVertex> {

        V data;
        double distance;

        LabeledVertex(V data, double distance) {
            this.data = data;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LabeledVertex that = (LabeledVertex) o;

            return data.equals(that.data);
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = data.hashCode();
            temp = Double.doubleToLongBits(distance);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public int compareTo(LabeledVertex o) {
            if (this.distance == o.distance)
                return 0;

            return (this.distance - o.distance < 0) ? -1 : 1;
        }

    }

}
