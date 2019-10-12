package graph;

/**
 * An edge is a connection between two vertices in a graph.
 *
 * @param <V> the type of vertex associated with this edge
 * @author Ryan Strauss
 */
public class DefaultEdge<V> {

    V source, target;

    public DefaultEdge(V source, V target) {
        this.source = source;
        this.target = target;
    }

    /**
     * Retrieves the source of this edge.
     *
     * @return the source of this edge
     */
    public V getSource() {
        return this.source;
    }

    /**
     * Retrieves the target of this edge.
     *
     * @return the target of this edge
     */
    public V getTarget() {
        return this.target;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DefaultEdge) {
            DefaultEdge other = (DefaultEdge) obj;
            return this.source.equals(other.source) && this.target.equals(other.target);
        }
        return false;
    }

}
