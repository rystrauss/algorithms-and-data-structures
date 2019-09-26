package list;

/**
 * Interface for a list of items.
 *
 * @param <T> The type of the elements in the list.
 * @author Ryan Strauss
 */
public interface List<T> extends Iterable<T> {

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
     * @param index  the location at which to add n
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
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param element element to be stored at the specified position
     * @param index   index of the element to replace
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T set(T element, int index) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the specified index, and returns that element.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T remove(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param element element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if
     * this list does not contain the element
     */
    public int indexOf(T element);

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     *
     * @param element the element to remove
     * @return true iff element was found in the list
     */
    public boolean remove(T element);

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
