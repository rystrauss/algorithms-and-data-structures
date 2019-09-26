package tree;

/**
 * Implementation of a Adelson-Velsky and Landis (AVL) self-balancing binary search tree.
 *
 * @param <E> the type of elements in the tree; must be comparable
 * @author Ryan Strauss
 */
public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {

    /**
     * Constructs an empty binary search tree.
     */
    public AVLTree() {
        super();
    }

    /**
     * Updates the level of a node based on the levels
     * of the left and right children.
     *
     * @param currentRoot Node that will have its level updated.
     */
    private void updateLevel(Node<E> currentRoot) {
        int level = 0;
        if (currentRoot.left != null)
            level = Math.max(level, currentRoot.left.level + 1);
        if (currentRoot.right != null)
            level = Math.max(level, currentRoot.right.level + 1);
        currentRoot.level = level;
    }

    /**
     * Calculates the height skew of a node based on the levels
     * of the left and right children.
     *
     * @param currentRoot Node that will have its height skew calculated.
     * @return The height skew: the difference between the right subtree's level
     * minus the left subtree's level. Note that this is:
     * - a positive number if the right subtree has a higher level;
     * - a negative number if the left subtree has a higher level.
     */
    private int calculateSkew(Node<E> currentRoot) {
        int leftLevel = currentRoot.left == null ? 0 : currentRoot.left.level + 1;
        int rightLevel = currentRoot.right == null ? 0 : currentRoot.right.level + 1;

        return rightLevel - leftLevel;
    }

    /**
     * Performs a left-side rotation around a top (parent) node and a pivot.
     * <p>
     * We expect that pivot is the right child of the top (parent) node.
     *
     * @param top   The top (parent) node.
     * @param pivot The pivot node.
     */
    private void rotateLeft(Node<E> top, Node<E> pivot) {
        top.right = pivot.left;
        pivot.left = top;

        pivot.parent = top.parent;
        top.parent = pivot;
        if (top.right != null)
            top.right.parent = top;

        if (pivot.parent == null)
            this.root = pivot;
        else {
            if (pivot.parent.left == top)
                pivot.parent.left = pivot;
            else
                pivot.parent.right = pivot;
        }

        updateLevel(top);
        updateLevel(pivot);
    }

    /**
     * Performs a right-side rotation around a top (parent) node and a pivot.
     * <p>
     * We expect that pivot is the left child of the top (parent) node.
     *
     * @param top   The top (parent) node.
     * @param pivot The pivot node.
     */
    private void rotateRight(Node<E> top, Node<E> pivot) {
        top.left = pivot.right;
        pivot.right = top;

        pivot.parent = top.parent;
        top.parent = pivot;
        if (top.left != null)
            top.left.parent = top;

        if (pivot.parent == null)
            this.root = pivot;
        else {
            if (pivot.parent.left == top)
                pivot.parent.left = pivot;
            else
                pivot.parent.right = pivot;
        }

        updateLevel(top);
        updateLevel(pivot);
    }

    /**
     * Rebalances a node by checking the height skew and performing
     * the appropriate rotations if necessary.
     *
     * @param currentRoot Node to be rebalanced.
     */
    private void rebalance(Node<E> currentRoot) {
        int skew = calculateSkew(currentRoot);
        if (skew >= 2) {
            if (calculateSkew(currentRoot.right) < 0)
                rotateRight(currentRoot.right, currentRoot.right.left);
            rotateLeft(currentRoot, currentRoot.right);
        } else if (skew <= -2) {
            if (calculateSkew(currentRoot.left) > 0)
                rotateLeft(currentRoot.left, currentRoot.left.right);
            rotateRight(currentRoot, currentRoot.left);
        }
    }

    @Override
    public boolean add(E element) {
        if (this.root == null) {
            this.root = new Node<>(element);
            return true;
        }
        return add(new Node<>(element), this.root);
    }


    /**
     * Adds a node into a (non-null) subtree rooted at currentRoot.
     *
     * @param node        The node being inserted.
     * @param currentRoot The root of the current subtree where we're adding the node.
     * @return True if the element was inserted; false if the value was already present.
     */
    private boolean add(Node<E> node, Node<E> currentRoot) {
        if (node.data.compareTo(currentRoot.data) < 0) {
            if (currentRoot.left != null) {
                boolean result = add(node, currentRoot.left);
                updateLevel(currentRoot);
                rebalance(currentRoot);
                return result;
            } else {
                currentRoot.left = node;
                node.parent = currentRoot;
                updateLevel(currentRoot);
                rebalance(currentRoot);
                return true;
            }
        } else if (node.data.compareTo(currentRoot.data) > 0) {
            if (currentRoot.right != null) {
                boolean result = add(node, currentRoot.right);
                updateLevel(currentRoot);
                rebalance(currentRoot);
                return result;
            } else {
                currentRoot.right = node;
                node.parent = currentRoot;
                updateLevel(currentRoot);
                rebalance(currentRoot);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean contains(E element) {
        return contains(element, this.root);
    }

    /**
     * Checks if a value is contained in the a subtree rooted at currentRoot.
     *
     * @param element     The value being checked for containment.
     * @param currentRoot The root of the current subtree where we're currently checking
     *                    the value for containment.
     * @return True if the value is present; false otherwise.
     */
    private boolean contains(E element, Node<E> currentRoot) {
        if (currentRoot == null)
            return false;

        if (element.equals(currentRoot.data))
            return true;

        if (element.compareTo(currentRoot.data) < 0)
            return contains(element, currentRoot.left);

        return contains(element, currentRoot.right);

    }

    /**
     * Removes an element from the tree.
     *
     * @param element Value to be removed from the tree.
     * @return True if the value was removed; false if the value was not found.
     */
    public boolean remove(E element) {
        return remove(element, root, null);
    }

    /**
     * Removes an element from the tree.
     *
     * @param element       Element to be removed from the tree.
     * @param currentRoot   Root of the current subtree where we're removing the value.
     * @param currentParent Parent of the currentRoot (null if currentRoot is the root).
     * @return True if the value was removed; false if the value was not found.
     */
    private boolean remove(E element, Node<E> currentRoot, Node<E> currentParent) {
        if (currentRoot == null)
            return false;

        if (element.compareTo(currentRoot.data) < 0) {
            boolean result = remove(element, currentRoot.left, currentRoot);
            updateLevel(currentRoot);
            rebalance(currentRoot);
            return result;
        }
        if (element.compareTo(currentRoot.data) > 0) {
            boolean result = remove(element, currentRoot.right, currentRoot);
            updateLevel(currentRoot);
            rebalance(currentRoot);
            return result;
        }

        if (currentRoot.left != null && currentRoot.right != null) {
            E min = minimumNode(currentRoot.right).data;
            remove(min, currentRoot.right, currentRoot);
            updateLevel(currentRoot);
            rebalance(currentRoot);
            currentRoot.data = min;
            return true;
        }

        Node<E> child = (currentRoot.left != null) ? currentRoot.left : currentRoot.right;

        if (currentParent == null) {
            this.root = child;
        } else {
            if (currentParent.left == currentRoot)
                currentParent.left = child;
            else
                currentParent.right = child;
        }

        if (child != null)
            child.parent = currentParent;

        return true;
    }

}
