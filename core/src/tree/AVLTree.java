package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Self-balancing binary search tree (AVL).
 *
 * @author Ryan Strauss
 */
public class AVLTree implements Tree {

    private Node root;
    private int size;

    /**
     * Constructs an empty binary search tree.
     */
    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * Updates the level of a node based on the levels
     * of the left and right children.
     *
     * @param currentRoot Node that will have its level updated.
     */
    private void updateLevel(Node currentRoot) {
        int leftLevel = currentRoot.left == null ? -1 : currentRoot.left.level;
        int rightLevel = currentRoot.right == null ? -1 : currentRoot.right.level;
        if (leftLevel == -1 && rightLevel == -1)
            currentRoot.level = 0;
        else
            currentRoot.level = Math.max(leftLevel, rightLevel) + 1;
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
    private int calculateSkew(Node currentRoot) {
        int right = -1;
        int left = -1;
        if (currentRoot.right != null)
            right = currentRoot.right.level;
        if (currentRoot.left != null)
            left = currentRoot.left.level;
        return right - left;
    }

    /**
     * Performs a left-side rotation around a top (parent) node and a pivot.
     * <p>
     * We expect that pivot is the right child of the top (parent) node.
     *
     * @param top   The top (parent) node.
     * @param pivot The pivot node.
     */
    private void rotateLeft(Node top, Node pivot) {
        pivot.parent = top.parent;
        if (pivot.parent == null)
            root = pivot;
        else {
            if (top.parent.left == top)
                top.parent.left = pivot;
            else
                top.parent.right = pivot;
        }
        top.parent = pivot;
        top.right = pivot.left;
        pivot.left = top;
        if (top.right != null)
            top.right.parent = top;
        updateLevel(top);
        updateLevel(pivot);
        if (pivot.parent != null)
            updateLevel(pivot.parent);
    }

    /**
     * Performs a right-side rotation around a top (parent) node and a pivot.
     * <p>
     * We expect that pivot is the left child of the top (parent) node.
     *
     * @param top   The top (parent) node.
     * @param pivot The pivot node.
     */
    private void rotateRight(Node top, Node pivot) {
        pivot.parent = top.parent;
        if (pivot.parent == null)
            root = pivot;
        else {
            if (top.parent.right == top)
                top.parent.right = pivot;
            else
                top.parent.left = pivot;
        }
        top.parent = pivot;
        top.left = pivot.right;
        pivot.right = top;
        if (top.left != null)
            top.left.parent = top;
        updateLevel(top);
        updateLevel(pivot);
        updateLevel(pivot.parent);
    }

    /**
     * Rebalances a node by checking the height skew and performing
     * the appropriate rotations if necessary.
     *
     * @param currentRoot Node to be rebalanced.
     */
    private void rebalance(Node currentRoot) {
        int skew = calculateSkew(currentRoot);
        if (skew > 1) {
            if (calculateSkew(currentRoot.right) < 0)
                rotateRight(currentRoot.right, currentRoot.right.left);
            rotateLeft(currentRoot, currentRoot.right);
        } else if (skew < -1) {
            if (calculateSkew(currentRoot.left) > 0)
                rotateLeft(currentRoot.left, currentRoot.left.right);
            rotateRight(currentRoot, currentRoot.left);
        }
    }

    /**
     * Adds an element into the tree.
     *
     * @param value The element to be inserted in the tree.
     * @return True if the element was inserted; false if the value was already present.
     */
    @Override
    public boolean add(int value) {
        Node temp = new Node(value);
        if (root == null) {
            root = temp;
            updateLevel(root);
            size++;
            return true;
        } else {
            if (add(temp, root)) {
                size++;
                return true;
            }
            return false;
        }
    }

    /**
     * Adds a node into a (non-null) subtree rooted at currentRoot.
     *
     * @param node        The node being inserted.
     * @param currentRoot The root of the current subtree where we're adding the node.
     * @return True if the element was inserted; false if the value was already present.
     */
    private boolean add(Node node, Node currentRoot) {
        if (node.value < currentRoot.value) {
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
        } else if (node.value > currentRoot.value) {
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

    /**
     * Returns true if a given value is contained in the tree.
     *
     * @param value The value being checked for containment.
     * @return True if the value is present; false otherwise.
     */
    @Override
    public boolean contains(int value) {
        return contains(value, root);
    }

    /**
     * Checks if a value is contained in the a subtree rooted at currentRoot.
     *
     * @param value       The value being checked for containment.
     * @param currentRoot The root of the current subtree where we're currently checking
     *                    the value for containment.
     * @return True if the value is present; false otherwise.
     */
    private boolean contains(int value, Node currentRoot) {
        if (currentRoot == null) {
            return false;
        }

        if (value == currentRoot.value) {
            return true;
        } else if (value < currentRoot.value) {
            return contains(value, currentRoot.left);
        } else {
            return contains(value, currentRoot.right);
        }
    }

    /**
     * Returns the minimum value of the tree.
     *
     * @return The minimum value of the tree, or -1 if the tree is empty.
     */
    public int minimumValue() {
        if (root == null) {
            return -1;
        }

        Node minimumNode = minimumNode(root);

        return minimumNode.value;
    }

    /**
     * Returns the node with the minimum key in the (non-null) subtree
     * rooted at currentRoot.
     *
     * @param currentRoot The root of the subtree that contains the minimum node.
     * @return The node with the minimum key in the (non-null) subtree
     * rooted at currentRoot.
     */
    private Node minimumNode(Node currentRoot) {
        if (currentRoot.left != null) {
            return minimumNode(currentRoot.left);
        }

        return currentRoot;
    }

    /**
     * Returns the maximum value of the tree.
     *
     * @return The maximum value of the tree, or -1 if the tree is empty.
     */
    public int maximumValue() {
        if (root == null) {
            return -1;
        }

        Node maximumNode = maximumNode(root);

        return maximumNode.value;
    }

    /**
     * Returns the node with the maximum key in the (non-null) subtree
     * rooted at currentRoot.
     *
     * @param currentRoot The root of the subtree that contains the maximum node.
     * @return The node with the maximum key in the (non-null) subtree
     * rooted at currentRoot.
     */
    private Node maximumNode(Node currentRoot) {
        if (currentRoot.right != null) {
            return maximumNode(currentRoot.right);
        }

        return currentRoot;
    }

    /**
     * Removes an element from the tree.
     *
     * @param value Value to be removed from the tree.
     * @return True if the value was removed; false if the value was not found.
     */
    @Override
    public boolean remove(int value) {
        return remove(value, root, null);
    }

    /**
     * Removes an element from the tree.
     *
     * @param value         Value to be removed from the tree.
     * @param currentRoot   Root of the current subtree where we're removing the value.
     * @param currentParent Parent of the currentRoot (null if currentRoot is the root).
     * @return True if the value was removed; false if the value was not found.
     */
    private boolean remove(int value, Node currentRoot, Node currentParent) {
        if (currentRoot == null) {
            return false;
        } else if (value < currentRoot.value) {
            boolean result = remove(value, currentRoot.left, currentRoot);
            rebalance(currentRoot);
            return result;
        } else if (value > currentRoot.value) {
            boolean result = remove(value, currentRoot.right, currentRoot);
            rebalance(currentRoot);
            return result;
        }

        if (currentRoot.left != null && currentRoot.right != null) {
            int min = minimumNode(currentRoot.right).value;
            remove(min, currentRoot.right, currentRoot);
            currentRoot.value = min;
            rebalance(currentRoot);
            return true;
        }

        Node child;

        if (currentRoot.left != null) {
            child = currentRoot.left;
        } else {
            child = currentRoot.right;
        }

        if (currentParent == null) {
            root = child;
        } else {
            if (currentParent.left == currentRoot) {
                currentParent.left = child;
            } else {
                currentParent.right = child;
            }
        }
        if (child != null)
            child.parent = currentParent;

        rebalance(currentRoot);
        return true;
    }

    /**
     * Performs a pre-order traversal of the tree.
     *
     * @return an ArrayList containing the elements of the tree in order of a pre-order traversal
     */
    @Override
    public List<Integer> preOrder() {
        List<Integer> list = new ArrayList<>(size);
        if (!isEmpty()) {
            preOrder(list, root);
        }
        return list;
    }

    /**
     * Recursive helper function for performing pre-order traversal.
     *
     * @param list   the list that elements are being added to
     * @param parent the next parent node
     */
    private void preOrder(List<Integer> list, Node parent) {
        if (parent != null) {
            list.add(parent.value);
            preOrder(list, parent.left);
            preOrder(list, parent.right);
        }
    }


    /**
     * Performs an in-order traversal of the tree.
     *
     * @return an ArrayList containing the elements of the tree in order of a in-order traversal
     */
    @Override
    public List<Integer> inOrder() {
        List<Integer> list = new ArrayList<>(size);
        if (!isEmpty()) {
            inOrder(list, root);
        }
        return list;
    }

    /**
     * Recursive helper function for performing in-order traversal.
     *
     * @param list   the list that elements are being added to
     * @param parent the next parent node
     */
    private void inOrder(List<Integer> list, Node parent) {
        if (parent != null) {
            inOrder(list, parent.left);
            list.add(parent.value);
            inOrder(list, parent.right);
        }
    }

    /**
     * Performs a post-order traversal of the tree.
     *
     * @return an ArrayList containing the elements of the tree in order of a post-order traversal
     */
    @Override
    public List<Integer> postOrder() {
        List<Integer> list = new ArrayList<>(size);
        if (!isEmpty()) {
            postOrder(list, root);
        }
        return list;
    }

    /**
     * Recursive helper function for performing post-order traversal.
     *
     * @param list   the list that elements are being added to
     * @param parent the next parent node
     */
    private void postOrder(List<Integer> list, Node parent) {
        if (parent != null) {
            postOrder(list, parent.left);
            postOrder(list, parent.right);
            list.add(parent.value);
        }
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return the size of the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns whether or not the tree is empty.
     *
     * @return true iff the tree contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
