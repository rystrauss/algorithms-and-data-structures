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
public class TreeSort {

    /**
     * Performs a binary tree sort on the given array.
     *
     * @param nums the numbers to be sorted
     * @return an ArrayList containing the elements of nums in sorted order, with duplicate
     * entries removed
     */
    public static List<Integer> sort(int[] nums) {
        BinarySearchTree<Integer> tree = new AVLTree<>();
        for (int i : nums) {
            tree.add(i);
        }
        return tree.inOrder();
    }

}
