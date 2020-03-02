package com.rystrauss.sort;

import java.util.List;

/**
 * Class containing implementation of merge sort.
 *
 * @author Ryan Strauss
 */
public class MergeSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(List<E> data) {
        Object[] dataArray = data.toArray();
        mergeSort(dataArray, 0, data.size() - 1);

        data.clear();
        for (Object o : dataArray)
            data.add((E) o);
    }

    /**
     * Sorts a subarray of an array of integers.
     *
     * @param data container array of subarray to be sorted
     * @param l    left boundary of subarray
     * @param r    right boundary of subarray
     */
    private void mergeSort(Object[] data, int l, int r) {
        //If subarray has less than two elements, stop.
        if (l >= r)
            return;

        //Find middle.
        int m = (l + r) / 2;

        //Sort the two halves.
        mergeSort(data, l, m);
        mergeSort(data, m + 1, r);

        //Merge the now-sorted halves.
        merge(data, l, m, r);
    }

    /**
     * Merges two sorted subarrays of an array of integers.
     *
     * @param data container array of subarrays to be merged
     * @param l    left boundary of subarrays
     * @param m    subarrays delimiter
     * @param r    right boundary of subarrays
     */
    private void merge(Object[] data, int l, int m, int r) {
        //Find length of left and right subarrays.
        int n1 = m - l + 1;
        int n2 = r - m;

        //Create temporary arrays to store subarrays.
        Object[] L = new Object[n1];
        Object[] R = new Object[n2];

        System.arraycopy(data, l, L, 0, n1);
        System.arraycopy(data, m + 1, R, 0, n2);

        //Initial indexes of temporary arrays.
        int i = 0, j = 0;

        //Initial index of merged subarray.
        int k = l;

        //Merge the arrays in sorted order.
        while (i < n1 && j < n2) {
            if (((E) L[i]).compareTo(((E) R[j])) <= 0)
                data[k++] = L[i++];
            else
                data[k++] = R[j++];
        }

        //Copy remaining elements of left subarray.
        while (i < n1) {
            data[k++] = L[i++];
        }

        //Copy remaining elements of right subarray.
        while (j < n2) {
            data[k++] = R[j++];
        }

    }

}
