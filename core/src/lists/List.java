package lists;

public interface List {

    /**
     * Adds the given integer to the end of the list.
     *
     * @param n the number to be added
     */
    public void add(int n);

    /**
     * Adds the given integer to the list at the given index.
     *
     * @param n     the number to be added
     * @param index the location at which to add n
     */
    public void add(int n, int index);

    /**
     * Gets the element at the specified index.
     *
     * @param index the index of the desired element
     * @return the element at index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public int get(int index) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the specified index, and returns that element.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public int remove(int index) throws IndexOutOfBoundsException;

    /**
     * Gets the size of the list.
     *
     * @return the size of the list
     */
    public int size();

    /**
     * Returns boolean value of whether or not the list is empty.
     *
     * @return true iff the list has at least one element.
     */
    public boolean isEmpty();

}
