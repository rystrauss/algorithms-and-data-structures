package sort;

import java.util.Arrays;

/**
 * Class containing implementation of counting sort.
 *
 * @author Ryan Strauss
 */
public class CountingSort {

    /**
     * Performs a counting sort on an array of integers on digit d.
     *
     * @param nums array to be sorted
     * @param d The digit on which to sort the elements. A value of 0 corresponds to the rightmost digit.
     */
    public static void sort(int[] nums, int d) {
        int[] count = new int[10];

        for (int i : nums)
            count[key(i, d)]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        int[] temp = Arrays.copyOf(nums, nums.length);

        for (int i : temp) {
            nums[count[key(i, d)]-- - 1] = i;
        }

    }

    /**
     * Gets the dth digit of num.
     */
    private static int key(int num, int d) {
        for (int i = 0; num != 0 && i < d; i++) num /= 10;
        return num % 10;
    }

}
