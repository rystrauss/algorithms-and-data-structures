public class Sort {

    /**
     * Performs an insertion sort on an array of integers.
     *
     * @param nums
     */
    public static void insertionSort(int[] nums) {
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

    /**
     * Performs a mergesort on the given array of integers.
     * @param nums
     */
    public static void mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
    }

    /**
     * Sorts a subarray of an array of integers.
     * @param nums
     * @param l
     * @param r
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
     * @param nums
     * @param l
     * @param m
     * @param r
     */
    private static void merge(int[] nums, int l, int m, int r) {
        //Find length of left and right subarrays.
        int n1 = m - l + 1;
        int n2 = r - m;

        //Create temporary arrays to store subarrays.
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++)
            L[i] = nums[l + i];
        for (int i = 0; i < n2; i++)
            R[i] = nums[m + 1 + i];

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
