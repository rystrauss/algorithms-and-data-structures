package com.rystrauss.sort;

import java.util.List;

/**
 * Interface for classes that implement sorting algorithms.
 *
 * @param <E> the type of the elements that this class will sort
 */
public abstract class Sorter<E extends Comparable<E>> {

    /**
     * Sorts the provided list of data.
     *
     * @param data the list to be sorted
     */
    public abstract void sort(List<E> data);

    /**
     * Swap elements at positions pos1 and pos2.
     *
     * @param pos1 First position.
     * @param pos2 Second position (no order implied).
     * @param data the list being operated on
     */
    void swap(int pos1, int pos2, List<E> data) {
        E temp = data.get(pos1);
        data.set(pos1, data.get(pos2));
        data.set(pos2, temp);
    }

}
