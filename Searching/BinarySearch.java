public class BinarySearch {

    /**
     * Performs a binary search for a target on a sorted array of integers.
     * @param target
     * @param nums
     * @return <code>true</code> if and only if <code>target</code> is in <code>nums</code>, otherwise <code>false</code>
     */
    public static boolean binarySearch(int target, int[] nums) {
        return binarySearch(target, nums, 0, nums.length - 1);
    }

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
