package com.rystrauss.sort;

import java.util.List;

/**
 * Class containing implementation of selection sort.
 *
 * @author Ryan Strauss
 */
public class SelectionSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(List<E> data) {
        int k = 0;
        while (k < data.size()) {
            int i = k;
            E min = data.get(i);
            int minIndex = i;
            while (i < data.size()) {
                if (data.get(i).compareTo(min) < 0) {
                    min = data.get(i);
                    minIndex = i;
                }
                i++;
            }
            E j = data.get(k);
            data.set(k, min);
            data.set(minIndex, j);
            k++;
        }
    }

}
