package search;

/**
 * Class containing implementation of binary search.
 */
public class BinarySearch {

    /**
     * Performs a binary search for a target on a sorted array of integers.
     *
     * @param target the item to search for
     * @param nums   the array to be searched
     * @return <code>true</code> if and only if <code>target</code> is in <code>nums</code>, otherwise <code>false</code>
     */
    public static boolean search(int target, int[] nums) {
        return binarySearch(target, nums, 0, nums.length - 1);
    }

    /*
    Recursive helper function that performs a binary search.
     */
    private static boolean binarySearch(int target, int[] nums, int start, int end) {
        if (start > end)
            return false;
        int middle = (start + end) / 2;
        if (target < nums[middle])
            return binarySearch(target, nums, start, middle - 1);
        if (target > nums[middle])
            return binarySearch(target, nums, middle + 1, end);
        return true;
    }

}
