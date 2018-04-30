import java.util.HashMap;
import java.util.Map;

public class ArrayAlgorithms {

    /**
     * Finds the maximum sum of a contiguous sub-array with Kadane's algorithm.
     *
     * @param arr
     * @return the maximum sum
     */
    public static int maxSum(int[] arr) {
        int thisMax = arr[0];
        int currMax = 0;
        for (int i = 1; i < arr.length; i++) {
            thisMax = Math.max(arr[i], thisMax + arr[i]);
            currMax = Math.max(currMax, thisMax);
        }
        return currMax;
    }

    /**
     * Given an array of size n-1 and given that there are numbers from 1 to n with
     * one missing, the missing number is to be found.
     *
     * @param arr
     * @return the missing number
     */
    public static int missingNumber(int[] arr) {
        int n = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i + 1 != arr[i]) {
                n = i + 1;
                break;
            }
        }
        return n;
    }

    /**
     * Finds the most frequent integer in an array.
     *
     * @param arr
     * @return
     */
    public static int mode(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i : arr) {
            if (frequency.containsKey(i))
                frequency.put(i, frequency.get(i) + 1);
            else
                frequency.put(i, 1);
        }
        int maxValue = Integer.MIN_VALUE;
        int maxFreq = Integer.MIN_VALUE;
        for (Map.Entry e : frequency.entrySet()) {
            if ((int) e.getValue() > maxFreq) {
                maxValue = (int) e.getKey();
                maxFreq = (int) e.getValue();
            }
        }
        return maxValue;
    }

    /**
     * Given an array of integers, finds
     * a continuous sub-array which adds to a given number.
     *
     * @param arr
     * @param sum
     * @return an empty string if the sum does not exist in the array, otherwise
     * a string of the form "startindex endindex"
     */
    public static String subarrayWithGivenSum(int[] arr, int sum) {
        String result = "";
        return result;
    }

    /**
     * Determines whether or not the given array has a pythagorean triplet
     * @param arr
     * @return <code>true</code> if and only if the array has a pythagorean triplet
     */
    public static boolean hasPythagoreanTriplet(int[] arr) {
        int[] temp = arr;
        for (int i = 0; i < temp.length; i++)
            temp[i] = temp[i] * temp[i];
        Sort.mergeSort(temp);
        for (int i = temp.length - 1; i >= 2; i--) {
            int l = 0;
            int r = i - 1;
            while (l < r) {
                int c = temp[i];
                int b = temp[r];
                int a = temp[l];
                if (a + b == c)
                    return true;
                if (arr[l] + arr[r] < arr[i])
                    l++;
                else
                    r--;
            }
        }
        return false;
    }

}
