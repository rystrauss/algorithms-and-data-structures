package com.rystrauss.sort;

/**
 * Class containing implementation of selection sort.
 *
 * @author Ryan Strauss
 */
public class SelectionSort {

    /**
     * Performs an inplace selection sort on an array of integers.
     *
     * @param nums array to be sorted
     */
    public static void sort(int[] nums) {
        int k = 0;
        while (k < nums.length) {
            int i = k;
            int min = nums[i];
            int minIndex = i;
            while (i < nums.length) {
                if (nums[i] < min) {
                    min = nums[i];
                    minIndex = i;
                }
                i++;
            }
            int j = nums[k];
            nums[k] = min;
            nums[minIndex] = j;
            k++;
        }
    }

}
