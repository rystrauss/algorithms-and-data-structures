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

    /**
     * Constructs an edge with a weight of {@code DEFAULT_EDGE_WEIGHT}.
     *
     * @param source the source vertex of the edge
     * @param target the target vertex of the edge
     */
    public Edge(Object source, Object target) {
        this(source, target, DEFAULT_EDGE_WEIGHT);
    }

    /**
     * Constructs an edge with a specified weight.
     *
     * @param source the source vertex of the edge
     * @param target the target vertex of the edge
     * @param weight the weight of the edge
     */
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (Double.compare(edge.weight, weight) != 0) return false;
        if (!source.equals(edge.source)) return false;
        return target.equals(edge.target);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = source.hashCode();
        result = 31 * result + target.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight == o.weight)
            return 0;

        return (this.weight < o.weight) ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "source=" + source +
                ", target=" + target +
                ", weight=" + weight +
                '}';
    }
}
