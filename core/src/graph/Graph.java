package graph;

/**
 * A graph is a structure comprised of a set of vertices, where any two vertices in the graph may or may not
 * be connected by an edge.
 *
 * @param <V> the type of vertices in this graph
 * @param <E> the type of edge in this graph
 * @author Ryan Strauss
 */
public interface Graph<V, E> extends Iterable<V> {
    
    /**
     * Creates a new edge in this graph, going from the source vertex to the target vertex, and returns
     * the created edge.
     *
     * @param source the source vertex of the edge
     * @param target the target vertex of the edge
     * @return the newly created edge if added to the graph, otherwise null
     * @throws IllegalArgumentException if source or target vertices are not found in the graph
     */
    E addEdge(V source, V target) throws IllegalArgumentException;

}
