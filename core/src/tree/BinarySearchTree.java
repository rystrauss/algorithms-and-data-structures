package tree;

import java.util.ArrayList;

/**
 * Interface for a binary search tree.
 *
 * @param <E> the type of elements in the tree; must be comparable
 * @author Ryan Strauss
 */
public interface BinarySearchTree<E extends Comparable<E>> {

    /**
     * Adds an element to the tree.
     *
     * @param element the element to be added to the tree
     * @return true iff the element was successfully added (i.e. the element was not already in the tree)
     */
    public boolean add(E element);

    /**
     * Removes an element from the tree.
     *
     * @param element the element to remove
     * @return true iff the element was successfully removed from the tree (i.e. if the element was in the tree)
     */
    public boolean remove(E element);

    /**
     * Determines whether or not an element is in the tree.
     *
     * @param element the element to check for
     * @return true iff the element is currently in the tree
     */
    public boolean contains(E element);

    /**
     * Performs an in-order traversal of the tree.
     *
     * @return an {@code ArrayList} containing the elements of the tree in in-order
     */
    public ArrayList<E> inOrder();

    /**
     * Performs a pre-order traversal of the tree.
     *
     * @return an {@code ArrayList} containing the elements of the tree in pre-order
     */
    public ArrayList<E> preOrder();

    /**
     * Performs a post-order traversal of the tree.
     *
     * @return an {@code ArrayList} containing the elements of the tree in post-order
     */
    public ArrayList<E> postOrder();

    /**
     * Gets the size of the tree.
     *
     * @return the number of elements in the tree
     */
    public int size();

    /**
     * Determines whether or not the tree is empty.
     *
     * @return true iff the tree is empty
     */
    public boolean isEmpty();

}
