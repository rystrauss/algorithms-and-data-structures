package utils;

import heaps.Heap;
import heaps.MinHeap;

/**
 * Supports the merging of an arbitrary number of already sorted arrays into a single sorted array.
 *
 * @author Ryan Strauss
 */
public class MergeKSorted {

    private static class Node implements Comparable<Node> {

        private int value;
        private int list;

        Node(int value, int list) {
            this.value = value;
            this.list = list;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    /**
     * Merges multiple sorted arrays into a single sorted array in O(n log k) time, where n is the total number of
     * elements and k is the number of presorted arrays.
     *
     * @param lists a array of sorted array (increasing order) of integers
     * @return a array of all the elements in sorted order
     */
    public static int[] merge(int[][] lists) {
        int k = lists.length;
        int n = 0;
        int[] indices = new int[k];
        Heap<Node> pq = new MinHeap<>();

        for (int i = 0; i < k; i++) {
            if (lists[i].length > 0)
                pq.push(new Node(lists[i][0], i));
            n += lists[i].length;
        }

        int[] combined = new int[n];

        for (int i = 0; i < n; i++) {
            int j = pq.peek().list;
            if (++indices[j] < lists[j].length) {
                Node next = new Node(lists[j][indices[j]], j);
                combined[i] = pq.replace(next).value;
            } else {
                combined[i] = pq.pop().value;
            }
        }

        return combined;
    }

}
