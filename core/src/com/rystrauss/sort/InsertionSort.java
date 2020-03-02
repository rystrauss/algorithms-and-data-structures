package com.rystrauss.sort;

/**
 * Class containing implementation of insertion sort.
 *
 * @author Ryan Strauss
 */
public class InsertionSort {

    /**
     * Performs an inplace insertion sort on an array of integers.
     *
     * @param nums array to be sorted
     */
    public static void sort(int[] nums) {
        int j;
        //Go through every element in the array.
        for (int i = 1; i < nums.length; i++) {
            j = i;
            //Move the next element into the sorted part of the array.
            while (j > 0 && nums[j] < nums[j - 1]) {
                //Swap elements until the element being moved is in the correct index.
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;
                j--;
            }
        }
    }

}
