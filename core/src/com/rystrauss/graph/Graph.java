package com.rystrauss.graph;

import java.util.Set;

/**
 * A graph is a structure comprised of a set of vertices, where any two vertices in the graph may or may not
 * be connected by an edge.
 * <p>
 * This interface is adapted from the JGraphT package.
 *
 * @param <V> the type of data stored in the vertices of this graph
 * @author Ryan Strauss
 */
public interface Graph<V> extends Iterable<V> {

    /**
     * Creates a new edge in this graph, going from the source vertex to the target vertex, and returns
     * the created edge.
     *
     * @param source the source vertex of the edge
     * @param target the target vertex of the edge
     * @return the newly created edge if added to the graph, otherwise null
     * @throws IllegalArgumentException if source or target vertices are not found in the graph
     */
    Edge addEdge(V source, V target) throws IllegalArgumentException;

    /**
     * Adds the specified vertex to this graph if not already present. If this graph already contains such vertex,
     * the call leaves this graph unchanged and returns false.
     *
     * @param v the vertex to be added to this graph
     * @return true iff this graph did not already contain the specified vertex
     */
    boolean addVertex(V v);

    /**
     * Returns an edge connecting source vertex to target vertex if such vertices and such edge
     * exist in this graph. Otherwise returns null.
     *
     * @param source source vertex of the edge
     * @param target target vertex of the edge
     * @return an edge connecting source vertex to target vertex
     */
    Edge getEdge(V source, V target);

    /**
     * Returns true if and only if this graph contains an edge going from the source vertex
     * to the target vertex. If any of the specified vertices does not exist in the graph, or if is null,
     * returns false.
     *
     * @param source source vertex of the edge
     * @param target target vertex of the edge
     * @return true if this graph contains the specified edge
     */
    boolean containsEdge(V source, V target);

    /**
     * Returns true if and only if this graph contains the specified edge.
     *
     * @param edge edge whose presence in this graph is to be tested
     * @return true if this graph contains the specified edge
     */
    boolean containsEdge(Edge edge);

    /**
     * Returns true if this graph contains the specified vertex. If the specified vertex is null, returns
     * false.
     *
     * @param v vertex whose presence in this graph is to be tested
     * @return true if this graph contains the specified vertex
     */
    boolean containsVertex(V v);

    /**
     * Returns a set of the edges contained in this graph.
     *
     * @return a set of the edges contained in this graph
     */
    Set<Edge> edgeSet();

}
