package com.rystrauss.sort;

import java.util.List;

/**
 * Implementation of insertion sort.
 *
 * @author Ryan Strauss
 */
public class InsertionSorter<E extends Comparable<E>> extends Sorter<E> {

    @Override
    public void sort(List<E> data) {
        int j;
        //Go through every element in the array.
        for (int i = 1; i < data.size(); i++) {
            j = i;
            //Move the next element into the sorted part of the array.
            while (j > 0 && data.get(j).compareTo(data.get(j - 1)) < 0) {
                //Swap elements until the element being moved is in the correct index.
                E temp = data.get(j);
                data.set(j, data.get(j - 1));
                data.set(j - 1, temp);
                j--;
            }
        }
    }
}
