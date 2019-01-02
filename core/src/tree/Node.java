package trees;

/**
 * Tree node.
 *
 * @author Ryan Strauss
 */
class Node {
    int value, level;
    Node left, right, parent;

    /**
     * Parameterized constructor for the <code>Node</code> class which sets the value of the node
     * equal to <code>value</code>.
     *
     * @param value the value of the new node
     */
    Node(int value) {
        this.value = value;
        left = null;
        right = null;
        parent = null;
        level = 0;
    }

    /**
     * Returns the <code>Node</code> as a <code>String</code> as "[value, level]".
     */
    public String toString() {
        return "[" + value + ", " + level + "]";
    }
}
