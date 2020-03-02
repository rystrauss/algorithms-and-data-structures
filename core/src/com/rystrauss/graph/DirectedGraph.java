package com.rystrauss.graph;

/**
 * Implements a directed graph (i.e. edges have a direction).
 *
 * @param <V> the type of data stored in the vertices of this graph
 * @author Ryan Strauss
 */
public class DirectedGraph<V> extends AbstractGraph<V> {

    /**
     * Constructs an empty undirected graph.
     */
    public DirectedGraph() {
        super();
    }

    @Override
    public Edge addEdge(V source, V target) throws IllegalArgumentException {
        if (!(incoming.containsKey(source) && incoming.containsKey(target))) {
            throw new IllegalArgumentException("Both the source and target vertices must exist in the graph " +
                    "in order to add an edge between them.");
        }

        Edge edge = new Edge(source, target);

        incoming.get(target).add(edge);
        outgoing.get(source).add(edge);

        return edge;
    }

}
