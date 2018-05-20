package sort;

/**
 * Class containing implementation of quick sort.
 *
 * @author Ryan Strauss
 */
public class QuickSort {

    /**
     * Performs an inplace quick sort on an array of integers.
     * <p>
     * This implementation uses the median-of-three approach when choosing a pivot.
     *
     * @param nums array to be sorted
     */
    public static void sort(int[] nums) {
        quicksort(nums, 0, nums.length - 1);
    }

    /**
     * Recursively performs quicksort on the specified section of the array.
     *
     * @param nums the array being sorted
     * @param low  the lower bound of the area to sort
     * @param high the upper bound of the area to sort
     */
    private static void quicksort(int[] nums, int low, int high) {
        if (low < high) {
            int p = partition(nums, low, high);
            quicksort(nums, low, p);
            quicksort(nums, p + 1, high);
        }
    }

    /**
     * Partitions the specified area of the array, and returns the partition point.
     *
     * @param nums the array being operated on
     * @param low  the lower bound of the area being partitioned
     * @param high the upper bound of the area being partitioned
     * @return the partition point
     */
    private static int partition(int[] nums, int low, int high) {
        int mid = low + (high - low) / 2;
        int pivot = median(nums[low], nums[mid], nums[high]);
        while (true) {
            while (nums[low] < pivot)
                low++;
            while (nums[high] > pivot)
                high--;
            if (low >= high)
                return high;
            swap(low, high, nums);
            low++;
            high--;
        }
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

    /**
     * Finds the median of the three inputs.
     *
     * @param a any number
     * @param b any number
     * @param c any number
     * @return the median of the three parameters
     */
    private static int median(int a, int b, int c) {
        if (a > b) {
            if (b > c) {
                return b;
            } else if (a > c) {
                return c;
            } else {
                return a;
            }
        } else {
            if (a > c) {
                return a;
            } else if (b > c) {
                return c;
            } else {
                return b;
            }
        }
    }

}
