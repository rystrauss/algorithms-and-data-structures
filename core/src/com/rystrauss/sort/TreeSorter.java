package com.rystrauss.sort;

import com.rystrauss.tree.AVLTree;
import com.rystrauss.tree.BinarySearchTree;

import java.util.List;

/**
 * Class containing implementation of binary tree sort.
 * <p>
 * The underlying AVL Tree excludes duplicate entries, so this class' sort method
 * removes duplicates during the sorting process.
 *
 * @author Ryan Strauss
 */
public class TreeSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(List<E> data) {
        BinarySearchTree<E> tree = new AVLTree<>();

        for (E e : data)
            tree.add(e);

        data.clear();
        data.addAll(tree.inOrder());
    }
}
