package tree;

import java.util.ArrayList;

/**
 * Implementation of a vanilla binary search tree.
 *
 * @param <E> the type of elements in the tree; must be comparable
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private Node<E> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Adds an element to the tree.
     *
     * @param element the element to be added to the tree
     * @return true iff the element was successfully added (i.e. the element was not already in the tree)
     */
    public boolean add(E element) {
        if (element == null)
            throw new NullPointerException("Trying to add null pointer to tree.");

        if (this.root == null) {
            this.root = new Node<>(element);
            this.size++;
            return true;
        }
        boolean result = add(element, this.root);
        if (result)
            this.size++;
        return result;
    }

    /**
     * Adds an element to the subtree rooted at {@code node}.
     *
     * @param element the element to be added
     * @param node    the root of the subtree that the element is being added to
     * @return true iff the subtree did not already contain the element
     */
    private boolean add(E element, Node<E> node) {
        if (element.compareTo(node.data) < 0) {
            if (node.left == null) {
                node.left = new Node<>(element);
                return true;
            }
            return add(element, node.left);
        } else if (element.compareTo(node.data) > 0) {
            if (node.right == null) {
                node.right = new Node<>(element);
                return true;
            }
            return add(element, node.right);
        }

        return false;
    }

    /**
     * Removes an element from the tree.
     *
     * @param element the element to remove
     * @return true iff the element was successfully removed from the tree (i.e. if the element was in the tree)
     */
    public boolean remove(E element) {
        MutableBoolean result = new MutableBoolean(true);
        remove(element, this.root, result);
        if (result.value)
            this.size--;
        return result.value;
    }

    /**
     * Recursive helper method for removing a node from the tree.
     *
     * @param element the element that should be removed (if present)
     * @param node    the root of the subtree that we are removing from
     * @param result  flag to indicate whether or not the element has been found in the tree
     * @return the root of the subtree that has had the element removed (if present)
     */
    private Node<E> remove(E element, Node<E> node, MutableBoolean result) {
        if (node == null) {
            // Set flag to indicate that the element was not found in the tree.
            result.value = false;
            return null;
        }

        if (element.compareTo(node.data) < 0)
            node.left = remove(element, node.left, result);
        else if (element.compareTo(node.data) > 0)
            node.right = remove(element, node.right, result);
        else {
            // At this point, we are at the node that we want to remove
            if (node.left != null && node.right != null) {
                // If the node has two children
                E min = minNode(node.right).data;
                node.data = min;
                node.right = remove(min, node.right, result);
            } else {
                // If the node has one or zero children
                return node.left != null ? node.left : node.right;
            }
        }

        return node;
    }

    /**
     * Finds the minimum value node in the subtree rooted at a node.
     *
     * @return the minimum value node in the subtree rooted at {@code node}
     */
    private Node<E> minNode(Node<E> node) {
        if (node.left == null)
            return node;

        return minNode(node.left);
    }

    /**
     * Determines whether or not an element is in the tree.
     *
     * @param element the element to check for
     * @return true iff the element is currently in the tree
     */
    public boolean contains(E element) {
        return contains(element, this.root);
    }

    /**
     * Recursively traverse the tree to search for the specified element.
     *
     * @param element the element that is being search for
     * @param node    the root of the subtree being searched
     * @return true iff the subtree rooted at {@code node} contains {@code element}; false otherwise
     */
    private boolean contains(E element, Node<E> node) {
        if (node == null)
            return false;
        if (element == node.data)
            return true;
        return contains(element, element.compareTo(node.data) < 0 ? node.left : node.right);
    }

    /**
     * Performs an in-order traversal of the tree.
     *
     * @return an {@code ArrayList} containing the elements of the tree in in-order
     */
    public ArrayList<E> inOrder() {
        ArrayList<E> elements = new ArrayList<>(this.size);
        inOrder(elements, this.root);
        return elements;
    }

    /**
     * Recursive helper function for performing in-order traversal.
     *
     * @param elements the list that elements are being added to
     * @param node     the next parent node
     */
    private void inOrder(ArrayList<E> elements, Node<E> node) {
        if (node != null) {
            inOrder(elements, node.left);
            elements.add(node.data);
            inOrder(elements, node.right);
        }
    }

    /**
     * Performs a pre-order traversal of the tree.
     *
     * @return an {@code ArrayList} containing the elements of the tree in pre-order
     */
    public ArrayList<E> preOrder() {
        ArrayList<E> elements = new ArrayList<>(this.size);
        inOrder(elements, this.root);
        return elements;
    }

    /**
     * Recursive helper function for performing pre-order traversal.
     *
     * @param elements the list that elements are being added to
     * @param node     the next parent node
     */
    private void preOrder(ArrayList<E> elements, Node<E> node) {
        if (node != null) {
            elements.add(node.data);
            preOrder(elements, node.left);
            preOrder(elements, node.right);
        }
    }

    /**
     * Performs a post-order traversal of the tree.
     *
     * @return an {@code ArrayList} containing the elements of the tree in post-order
     */
    public ArrayList<E> postOrder() {
        ArrayList<E> elements = new ArrayList<>(this.size);
        inOrder(elements, this.root);
        return elements;
    }

    /**
     * Recursive helper function for performing post-order traversal.
     *
     * @param elements the list that elements are being added to
     * @param node     the next parent node
     */
    private void postOrder(ArrayList<E> elements, Node<E> node) {
        if (node != null) {
            postOrder(elements, node.left);
            postOrder(elements, node.right);
            elements.add(node.data);
        }
    }

    /**
     * Gets the size of the tree.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        return this.size;
    }

    /**
     * Determines whether or not the tree is empty.
     *
     * @return true iff the tree is empty
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Thin wrapper class around the boolean primitive that effectively allows for passing boolean by reference.
     */
    private class MutableBoolean {

        boolean value;

        MutableBoolean(boolean value) {
            this.value = value;
        }

    }

}
