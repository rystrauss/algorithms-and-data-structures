package com.rystrauss.graph;

/**
 * Implements an undirected graph (i.e. every edge can be traversed in both directions).
 *
 * @param <V> the type of data stored in the vertices of this graph
 * @author Ryan Strauss
 */
public class UndirectedGraph<V> extends AbstractGraph<V> {

    /**
     * Constructs an empty undirected graph.
     */
    public UndirectedGraph() {
        super();
    }

    @Override
    public Edge addEdge(V source, V target) throws IllegalArgumentException {
        if (!(incoming.containsKey(source) && incoming.containsKey(target))) {
            throw new IllegalArgumentException("Both the source and target vertices must exist in the graph " +
                    "in order to add an edge between them.");
        }

        Edge edge1 = new Edge(source, target);
        Edge edge2 = new Edge(target, source);

        incoming.get(target).add(edge1);
        outgoing.get(source).add(edge1);
        incoming.get(source).add(edge2);
        outgoing.get(target).add(edge2);

        return edge1;
    }

}
