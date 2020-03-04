package com.rystrauss.sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeapSorterTest extends SorterTest {

    @Test
    void sortIntegers() {
        Sorter<Integer> sorter = new HeapSorter<>();
        List<Integer> data = new ArrayList<>();

        for (int i = 0; i < LIST_SIZE; i++) {
            data.add((int) (Math.random() * LIST_SIZE));
        }

        sorter.sort(data);
        assertTrue(isSorted(data), "List is not sorted.");
    }

    @Test
    void sortDoubles() {
        Sorter<Double> sorter = new HeapSorter<>();
        List<Double> data = new ArrayList<>();

        for (int i = 0; i < LIST_SIZE; i++) {
            data.add(Math.random() * LIST_SIZE);
        }

        sorter.sort(data);
        assertTrue(isSorted(data), "List is not sorted.");
    }

}