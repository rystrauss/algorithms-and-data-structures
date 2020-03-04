package com.rystrauss.sort;

import java.util.List;

class SorterTest {

    static final int LIST_SIZE = 10000;

    /**
     * Determines whether or not a list is sorted.
     *
     * @param data the list of data to be verified
     * @param <E>  the type of elements in the list
     * @return true iff the list is monotonically ordered
     */
    static <E extends Comparable<E>> boolean isSorted(List<E> data) {
        E prev = data.get(0);
        for (E e : data) {
            if (e.compareTo(prev) < 0)
                return false;
        }
        return true;
    }

}