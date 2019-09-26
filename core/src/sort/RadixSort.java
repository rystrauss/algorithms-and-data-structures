package sort;

/**
 * Class containing implementation of counting sort.
 *
 * @author Ryan Strauss
 */
public class RadixSort {

    /**
     * Performs a radix sort on the given array.
     * This implementation assumes positive elements only.
     *
     * @param nums the array to be sorted
     */
    public static void sort(int[] nums) {
        for (int i = 0; i < maxDigits(nums); i++) {
            CountingSort.sort(nums, i);
        }
    }

    /**
     * Gets the number of digits of the largest integer in an array.
     */
    private static int maxDigits(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i > max) max = i;
        }
        return (int) (Math.log10(max) + 1);
    }

}
