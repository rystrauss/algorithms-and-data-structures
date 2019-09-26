package search;

import java.util.ArrayList;

/**
 * Class containing implementation of binary search.
 *
 * @author Ryan Strauss
 */
public class BinarySearch {

    /**
     * Performs a binary search on a list.
     * <p>
     * It is assumed that the list is already sorted.
     *
     * @param target The element being searched for.
     * @param list   A sorted list; must be an ArrayList so access time is constant.
     * @param <T>    The type of element being searched for; must be comparable.
     * @return The index in the list of the target, or -1 if it was not found.
     */
    public static <T extends Comparable<? super T>> int search(T target, ArrayList<T> list) {
        return binarySearch(target, list, 0, list.size() - 1);
    }

    /**
     * Recursively executes a binary search.
     */
    private static <T extends Comparable<? super T>> int binarySearch(T target, ArrayList<T> list, int start, int end) {
        if (start > end)
            return -1;
        int middle = (start + end) / 2;
        if (target.compareTo(list.get(middle)) < 0)
            return binarySearch(target, list, start, middle - 1);
        if (target.compareTo(list.get(middle)) > 0)
            return binarySearch(target, list, middle + 1, end);
        return middle;
    }

}
