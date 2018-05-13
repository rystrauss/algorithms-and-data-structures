import sort.MergeSort;

import java.util.Arrays;

public class Tester {

    public static void main(String[] args) {
        int[] nums = {4, 6, 2, 8, 8, 9, 3, 6, 5, 1, 3, 6, 8, 4, 6, 2, 8, 5};
        System.out.println(Arrays.toString(nums));
        MergeSort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
