package com.rystrauss.sort;

import java.util.List;

/**
 * Class containing implementation of heap sort.
 *
 * @author Ryan Strauss
 */
public class HeapSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(List<E> data) {
        heapify(data);
        int end = data.size() - 1;
        while (end > 0) {
            swap(0, end, data);
            end--;
            siftDown(data, 0, end);
        }
    }

    /**
     * Rearranges the elements of the given list to satisfy the max-heap property.
     *
     * @param data the list to be transformed.
     */
    private void heapify(List<E> data) {
        int end = data.size() - 1;
        int start = parent(end);
        while (start >= 0) {
            siftDown(data, start, end);
            start--;
        }
    }

    /**
     * Performs a max-heap sift down operation.
     *
     * @param data  the list to perform the operation on; this list is assumed to be a max-heap
     * @param start the index of the element to sift
     * @param end   the index of the end of the heap
     */
    private void siftDown(List<E> data, int start, int end) {
        int root = start;
        while (true) {
            int swap = root;
            if (leftChild(root) <= end && data.get(swap).compareTo(data.get(leftChild(root))) < 0) {
                swap = leftChild(root);
            }
            if (rightChild(root) <= end && data.get(swap).compareTo(data.get(rightChild(root))) < 0) {
                swap = rightChild(root);
            }
            if (swap == root) {
                return;
            }
            swap(swap, root, data);
            root = swap;
        }
    }

    /**
     * Given a position i, return the position of the parent node.
     *
     * @param i Position to check for the parent node.
     * @return Position of the parent node.
     */
    private static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Given a position i, return the position of the left child.
     *
     * @param i Position to check for the left child.
     * @return Position of the left child.
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Given a position i, return the position of the right child.
     *
     * @param i Position to check for the right child.
     * @return Position of the right child.
     */
    private static int rightChild(int i) {
        return 2 * i + 2;
    }

}
