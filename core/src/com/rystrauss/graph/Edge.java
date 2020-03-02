package com.rystrauss.graph;

/**
 * An edge is a connection between two vertices in a graph.
 * <p>
 * This edge is also weighted. Intuitively, the weight is often used to represent some
 * sort of cost associated with traversing the edge.
 *
 * @author Ryan Strauss
 */
public class Edge implements Comparable<Edge> {

    /**
     * The default weight for an edge.
     */
    public static final double DEFAULT_EDGE_WEIGHT = 1.0;

    private Object source, target;
    private double weight;

    public Edge(Object source, Object target) {
        this(source, target, DEFAULT_EDGE_WEIGHT);
    }

    public Edge(Object source, Object target, double weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }

    /**
     * Retrieves the source of this edge.
     *
     * @return the source of this edge
     */
    protected Object getSource() {
        return this.source;
    }

    /**
     * Retrieves the target of this edge.
     *
     * @return the target of this edge
     */
    protected Object getTarget() {
        return this.target;
    }

    /**
     * Retrieves the weight of this edge.
     *
     * @return the weight of this edge
     */
    protected double getWeight() {
        return this.weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Edge) {
            Edge other = (Edge) obj;
            return this.source.equals(other.source)
                    && this.target.equals(other.target)
                    && this.weight == other.weight;
        }
        return false;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight == o.weight)
            return 0;

        return (this.weight < o.weight) ? -1 : 1;
    }

}
