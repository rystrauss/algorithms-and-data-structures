package com.rystrauss.dp;

import java.util.LinkedList;
import java.util.List;

/**
 * Dynamic programming solver for the 0-1 Knapsack problem.
 *
 * @author Ryan Strauss
 */
public class Knapsack {

    /**
     * Solves the Knapsack problem.
     *
     * @param maxSize the maximum size of the "bag"
     * @param sizes   an array where index i is the size of the ith item
     * @param values  an array where index i is the value of the ith item
     * @return a list there the first element is the maximum value that can be fit in the bag
     * and the remaining elements are the items to take which achieve that value
     */
    public static List<Integer> solve(int maxSize, int[] sizes, int[] values) {
        if (sizes.length != values.length)
            throw new IllegalArgumentException("The sizes and values arrays must be of the same length.");

        int n = sizes.length;
        int[][] table = new int[n + 1][maxSize + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxSize; j++) {
                if (sizes[i - 1] <= j) {
                    table[i][j] = Math.max(values[i - 1] + table[i - 1][j - sizes[i - 1]], table[i - 1][j]);
                } else {
                    table[i][j] = table[i - 1][j];
                }
            }
        }

        List<Integer> indices = new LinkedList<>();
        indices.add(table[n][maxSize]);
        int i = n, j = maxSize;
        while (i > 0 && j > 0) {
            if (table[i][j] != table[i - 1][j]) {
                indices.add(i - 1);
                j -= sizes[i - 1];
            }
            i--;
        }

        return indices;
    }

}
