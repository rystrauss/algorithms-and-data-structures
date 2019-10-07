package collections;

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the
 * list each element is inserted. The user can access elements by their integer index (position in the list), and
 * search for elements in the list.
 *
 * @param <E> the type of elements in this list
 * @author Ryan Strauss
 */
public interface List<E> extends Collection<E> {

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that
     * position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    void add(int index, E element) throws IndexOutOfBoundsException;

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the
     * element currently at that position (if any) and any subsequent elements to the right (increases their indices).
     * The new elements will appear in this list in the order that they are returned by the specified collection's
     * iterator.
     *
     * @param index index at which to insert the first element from the specified collection
     * @param c     collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    boolean addAll(int index, Collection<? extends E> c) throws IndexOutOfBoundsException;

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E get(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. More formally, returns the lowest index i such that
     * (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does
     * not contain the element
     */
    int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not
     * contain the element. More formally, returns the highest index i such that
     * (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does
     * not contain the element
     */
    int lastIndexOf(Object o);

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left
     * (subtracts one from their indices). Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E remove(int index) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    E set(int index, E element) throws IndexOutOfBoundsException;

}
