package com.rystrauss.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Class containing implementation of counting sort.
 *
 * @author Ryan Strauss
 */
public class RadixSorter extends Sorter<Integer> {

    @Override
    public void sort(List<Integer> data) {
        for (int i = 0; i < maxDigits(data); i++)
            countingSort(data, i);
    }

    /**
     * Performs a counting sort on an array of integers on digit d.
     *
     * @param data the list to be sorted
     * @param d    The digit on which to sort the elements. A value of 0 corresponds to the rightmost digit.
     */
    public void countingSort(List<Integer> data, int d) {
        int[] count = new int[10];

        for (int i : data)
            count[key(i, d)]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        List<Integer> temp = new ArrayList<>(data);

        for (int i = data.size() - 1; i >= 0; i--)
            data.set(--count[key(temp.get(i), d)], temp.get(i));
    }

    /**
     * Gets the dth digit of num.
     */
    private static int key(int num, int d) {
        for (int i = 0; num != 0 && i < d; i++) num /= 10;
        return num % 10;
    }

    /**
     * Gets the number of digits of the largest integer in a list.
     */
    private static int maxDigits(List<Integer> data) {
        int max = Integer.MIN_VALUE;
        for (int i : data) {
            if (i > max) max = i;
        }
        return (int) (Math.log10(max) + 1);
    }


}
