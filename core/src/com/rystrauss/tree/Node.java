package com.rystrauss.tree;

/**
 * Class that represents a node within a tree.
 *
 * @param <E> the type of the data contained in the node
 * @author Ryan Strauss
 */
class Node<E extends Comparable<E>> {

    Node<E> parent, left, right;
    E data;
    int level;

    Node(E data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
        this.level = 0;
    }

}
