package sort;

/**
 * Class containing implementation of merge sort.
 *
 * @author Ryan Strauss
 */
public class MergeSort {

    /**
     * Performs an inplace merge sort on the given array of integers.
     *
     * @param nums the array to be sorted
     */
    public static void sort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    /**
     * Sorts a subarray of an array of integers.
     *
     * @param nums container array of subarray to be sorted
     * @param l left boundary of subarray
     * @param r right boundary of subarray
     */
    private static void mergeSort(int[] nums, int l, int r) {
        //If subarray has less than two elements, stop.
        if (l >= r)
            return;

        //Find middle.
        int m = (l + r) / 2;

        //Sort the two halves.
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, r);

        //Merge the now-sorted halves.
        merge(nums, l, m, r);
    }

    /**
     * Merges two sorted subarrays of an array of integers.
     *
     * @param nums container array of subarrays to be merged
     * @param l left boundary of subarrays
     * @param m subarrays delimiter
     * @param r right boundary of subarrays
     */
    private static void merge(int[] nums, int l, int m, int r) {
        //Find length of left and right subarrays.
        int n1 = m - l + 1;
        int n2 = r - m;

        //Create temporary arrays to store subarrays.
        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(nums, l, L, 0, n1);
        System.arraycopy(nums, m + 1, R, 0, n2);

        //Initial indexes of temporary arrays.
        int i = 0, j = 0;

        //Initial index of merged subarray.
        int k = l;

        //Merge the arrays in sorted order.
        while (i < n1 && j < n2) {
            if (L[i] <= R[j])
                nums[k++] = L[i++];
            else
                nums[k++] = R[j++];
        }

        //Copy remaining elements of left subarray.
        while (i < n1) {
            nums[k++] = L[i++];
        }

        //Copy remaining elements of right subarray.
        while (j < n2) {
            nums[k++] = R[j++];
        }

    }

}
