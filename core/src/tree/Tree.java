package trees;

import java.util.List;

/**
 * Interface for a Tree data structure.
 *
 * @author Ryan Strauss
 */
public interface Tree {

    /**
     * Adds an element into the tree.
     *
     * @param value The element to be inserted in the tree.
     * @return True if the element was inserted; false if the value was already present.
     */
    public boolean add(int value);

    /**
     * Removes an element from the tree.
     *
     * @param value Value to be removed from the tree.
     * @return True if the value was removed; false if the value was not found.
     */
    public boolean remove(int value);

    /**
     * Returns true if a given value is contained in the tree.
     *
     * @param value The value being checked for containment.
     * @return True if the value is present; false otherwise.
     */
    public boolean contains(int value);

    /**
     * Performs a pre-order traversal of the tree.
     *
     * @return an array containing the elements of the tree in order of a pre-order traversal
     */
    public List<Integer> preOrder();

    /**
     * Performs an in-order traversal of the tree.
     *
     * @return an array containing the elements of the tree in order of a in-order traversal
     */
    public List<Integer> inOrder();

    /**
     * Performs a post-order traversal of the tree.
     *
     * @return an array containing the elements of the tree in order of a post-order traversal
     */
    public List<Integer> postOrder();

    /**
     * Returns the number of elements in the tree.
     *
     * @return the size of the tree
     */
    public int size();

    /**
     * Returns whether or not the tree is empty.
     *
     * @return true iff the tree contains no elements
     */
    public boolean isEmpty();

}
