package string;

import list.LinkedList;
import list.List;

/**
 * Class containing implementation of the Knuth-Morris-Pratt algorithm.
 *
 * @author Ryan Strauss
 */
public class KMP {

    /**
     * Determines whether one String is a cyclic rotation of another.
     * For example, "car" is a cyclic rotation of "arc".
     *
     * This implementation runs in linear time.
     *
     * @param a the first String
     * @param b the second String
     * @return true iff a and b are cyclic rotations of each other, false otherwise
     */
    public static boolean isCyclicRotation(String a, String b) {
        return a.length() == b.length() && !search(a + a, b).isEmpty();
    }

    /**
     * Implementation of the Knuth-Morris-Pratt matching algorithm.
     * This algorithm searches for a substring within a string in linear time.
     * Method will return a List containing all indexes at which the substring p
     * is found within the string t.
     *
     * @param target  the target string
     * @param pattern the substring that is trying to be matched to the target
     * @return a List of integers containing all indices in t where p is found
     */
    public static List<Integer> search(String target, String pattern) {
        List<Integer> indices = new LinkedList<>();
        if (target.isEmpty() || pattern.isEmpty())
            return indices;
        char[] t = target.toCharArray();
        char[] p = pattern.toCharArray();
        int n = t.length;
        int m = p.length;

        int[] pi = computePrefixFunction(t);

        int q = 0;

        for (int i = 0; i < n; i++) {
            while (q > 0 && p[q] != t[i])
                q = pi[q - 1];
            if (p[q] == t[i])
                q++;
            if (q == m) {
                indices.add(i - m + 1);
                q = pi[q];
            }
        }

        return indices;
    }

    /**
     * Computes the prefix function of a pattern (given as a char array) as used in the KMP algorithm.
     *
     * @param p the pattern for which to compute the prefix function
     * @return the prefix function of p as an int array
     */
    private static int[] computePrefixFunction(char[] p) {
        int m = p.length;
        int[] pi = new int[m];
        int k = 0;
        for (int q = 1; q < m; q++) {
            while (k > 0 && p[k] != p[q])
                k = pi[k - 1];
            if (p[k] == p[q])
                k++;
            pi[q] = k;
        }
        return pi;
    }

}
