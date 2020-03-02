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

}
