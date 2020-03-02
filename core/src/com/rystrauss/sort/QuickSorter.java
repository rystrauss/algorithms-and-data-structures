package com.rystrauss.sort;

import java.util.List;

/**
 * Class containing implementation of quick sort.
 *
 * @author Ryan Strauss
 */
public class QuickSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(List<E> data) {
        quicksort(data, 0, data.size() - 1);
    }

    /**
     * Recursively performs quicksort on the specified section of the array.
     *
     * @param data the list being sorted
     * @param low  the lower bound of the area to sort
     * @param high the upper bound of the area to sort
     */
    private void quicksort(List<E> data, int low, int high) {
        if (low < high) {
            int p = partition(data, low, high);
            quicksort(data, low, p);
            quicksort(data, p + 1, high);
        }
    }

    /**
     * Partitions the specified area of the array, and returns the partition point.
     *
     * @param data the array being operated on
     * @param low  the lower bound of the area being partitioned
     * @param high the upper bound of the area being partitioned
     * @return the partition point
     */
    private int partition(List<E> data, int low, int high) {
        E pivot = data.get((int) (Math.random() * (high - low)) + low);

        while (true) {
            while (data.get(low).compareTo(pivot) < 0)
                low++;
            while (data.get(high).compareTo(pivot) > 0)
                high--;
            if (low >= high)
                return high;

            swap(low, high, data);
            low++;
            high--;
        }
    }

}
