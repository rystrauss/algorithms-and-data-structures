package graph;

/**
 * A weighted edge is identical to a standard edge except that it contains an associated weight.
 * Intuitively, the weight is often used to represent some sort of cost associated with traversing the edge.
 *
 * @param <V> the type of vertex associated with this edge
 */
public class WeightedEdge<V> extends DefaultEdge<V> implements Comparable<WeightedEdge> {

    private double weight;

    public WeightedEdge(V source, V target) {
        this(source, target, 0.0);
    }

    public WeightedEdge(V source, V target, double weight) {
        super(source, target);
        this.weight = weight;
    }

    /**
     * Retrieves the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double getWeight() {
        return this.weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WeightedEdge) {
            WeightedEdge other = (WeightedEdge) obj;
            return this.source.equals(other.source)
                    && this.target.equals(other.target)
                    && this.weight == other.weight;
        }
        return false;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        if (this.weight == o.weight)
            return 0;

        return (this.weight < o.weight) ? -1 : 1;
    }

}
