package tree;

/**
 * Class that represents a node within a tree.
 *
 * @param <E> the type of the data contained in the node
 * @author Ryan Strauss
 */
class Node<E extends Comparable<E>> {

    Node<E> parent, left, right;
    E data;

    Node(E data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

}
