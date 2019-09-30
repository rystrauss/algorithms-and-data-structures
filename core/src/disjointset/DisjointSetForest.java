package disjointset;

import java.util.*;

/**
 * A disjoint-set data structure (a.k.a union-find).
 * <p>
 * This data structure tracks a set of elements partitioned into a number of disjoint subsets. It provides
 * near-constant-time operations to add new sets, to merge existing sets, and to determine whether elements
 * are in the same set.
 * <p>
 * This implementation uses two heuristics: union by rank and path compression.
 *
 * @param <E> the elements stored in the data structure
 * @author Ryan Strauss
 */
public class DisjointSetForest<E extends Comparable<E>> {

    private int numSubsets;
    private Map<E, Node> elementMap;

    private class Node {

        Node parent;
        E data;
        int size, rank;

        Node(E data) {
            this.data = data;
            this.parent = null;
            this.size = 1;
            this.rank = 0;
        }

    }

    /**
     * Makes a new disjoint-set forest containing all of the elements in the provided list. Initially, all elements
     * are disjoint.
     * <p>
     * Note that duplicate elements in the provided list will only be added once.
     *
     * @param elements a list of elements to be put into the forest
     */
    public DisjointSetForest(List<E> elements) {
        this.numSubsets = elements.size();
        this.elementMap = new HashMap<>();

        for (E e : elements)
            this.elementMap.put(e, new Node(e));
    }

    /**
     * Finds the representative element for the provided element's subset.
     *
     * @param element the element whose representative will be returned
     * @return the representative for {@code element}'s subset
     */
    public E find(E element) {
        List<Node> path = new LinkedList<>();
        Node current = this.elementMap.get(element);

        if (current == null)
            throw new IllegalStateException("Provided element does not exist in the disjoint set.");

        while (current.parent != null) {
            path.add(current);
            current = current.parent;
        }

        for (Node n : path)
            n.parent = current;

        return current.data;
    }

    /**
     * Merges the subsets of the supplied elements.
     * <p>
     * This method assumes that the provided elements are the representatives of their subsets (representatives
     * can be found with the {@code find} method.
     *
     * @param element1 the first element to be merged
     * @param element2 the second element to be merged
     */
    public void union(E element1, E element2) {
        Node n1 = this.elementMap.get(element1);
        Node n2 = this.elementMap.get(element2);

        if (n1 == null || n2 == null)
            throw new IllegalStateException("Provided elements cannot be found in the disjoint set.");

        if (n1.parent != null || n2.parent != null)
            throw new IllegalStateException("Both elements must be the representatives for their subsets.");

        if (n1.rank >= n2.rank) {
            n2.parent = n1;
            n1.size += n2.size;
            if (n1.rank == n2.rank)
                n1.rank++;
        } else {
            n1.parent = n2;
            n2.size += n1.size;
        }

        this.numSubsets--;
    }

    /**
     * Returns the subsets of the disjoint set forest.
     *
     * @return a Map from the representative elements to a List of the elements in the subset
     */
    public Map<E, List<E>> getSubsets() {
        Map<E, List<E>> segments = new HashMap<>();

        for (E e : this.elementMap.keySet()) {
            E rep = find(e);
            if (!segments.containsKey(rep))
                segments.put(rep, new LinkedList<>());
            segments.get(rep).add(e);
        }

        return segments;
    }

    /**
     * Gets the number of subsets in the disjoint set.
     *
     * @return the number of subsets
     */
    public int getNumSubsets() {
        return this.numSubsets;
    }

}
