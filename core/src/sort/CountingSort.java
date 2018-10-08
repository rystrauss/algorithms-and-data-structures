package sort;

/**
 * Class containing implementation of counting sort.
 *
 * @author Ryan Strauss
 */
public class CountingSort {

    /**
     * Performs an inplace counting sort on an array of integers between 0 and 9.
     *
     * @param nums array to be sorted
     */
    public static void sort(int[] nums) {
        int[] count = new int[10];

        for (int i : nums) count[i]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];

        int index = nums.length - 1;

        for (int i = 9; i > 0; i--) {
            while (count[i] - count[i - 1] > 0) {
                nums[index--] = i;
                count[i]--;
            }
        }

        for (int i = 0; i < count[0]; i++) nums[i] = 0;

    }

}
