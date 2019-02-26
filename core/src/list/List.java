package list;

public interface List<T> {

    /**
     * Adds the given integer to the end of the list.
     *
     * @param object the object to be added
     */
    public void add(T object);

    /**
     * Adds the given integer to the list at the given index.
     *
     * @param object the object to be added
     * @param index the location at which to add n
     */
    public void add(T object, int index);

    /**
     * Gets the element at the specified index.
     *
     * @param index the index of the desired element
     * @return the element at index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T get(int index) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the specified index, and returns that element.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T remove(int index) throws IndexOutOfBoundsException;

    /**
     * Reverses the list.
     */
    public void reverse();

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
