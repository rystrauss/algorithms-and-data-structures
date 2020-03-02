package com.rystrauss.sort;

/**
 * Class containing implementation of heap sort.
 *
 * @author Ryan Strauss
 */
public class HeapSort {

    /**
     * Performs an inplace heap sort on an array of integers.
     *
     * @param nums array to be sorted
     */
    public static void sort(int[] nums) {
        heapify(nums);
        int end = nums.length - 1;
        while (end > 0) {
            swap(0, end, nums);
            end--;
            siftDown(nums, 0, end);
        }
    }

    /**
     * Rearranges the elements of the given array to satisfy the max-heap property.
     *
     * @param nums the array to be transformed.
     */
    private static void heapify(int[] nums) {
        int end = nums.length - 1;
        int start = parent(end);
        while (start >= 0) {
            siftDown(nums, start, end);
            start--;
        }
    }

    /**
     * Performs a max-heap sift down operation.
     *
     * @param nums  the array to perform the operation on; this array is assumed to be a max-heap
     * @param start the index of the element to sift
     * @param end   the index of the end of the heap
     */
    private static void siftDown(int[] nums, int start, int end) {
        int root = start;
        while (true) {
            int swap = root;
            if (leftChild(root) <= end && nums[swap] < nums[leftChild(root)]) {
                swap = leftChild(root);
            }
            if (rightChild(root) <= end && nums[swap] < nums[rightChild(root)]) {
                swap = rightChild(root);
            }
            if (swap == root) {
                return;
            }
            swap(swap, root, nums);
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

    /**
     * Swap elements at positions pos1 and pos2.
     *
     * @param pos1 First position.
     * @param pos2 Second position (no order implied).
     * @param nums the array being operated on
     */
    private static void swap(int pos1, int pos2, int[] nums) {
        int temporary = nums[pos1];
        nums[pos1] = nums[pos2];
        nums[pos2] = temporary;
    }

}
