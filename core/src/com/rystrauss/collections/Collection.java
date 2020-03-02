package com.rystrauss.collections;

import java.util.Iterator;

/**
 * A collection represents a group of objects, known as its elements. Some collections allow duplicate elements
 * and others do not. Some are ordered and others unordered.
 * <p>
 * This interface is based on Java's Collection interface.
 *
 * @param <E> the type of elements in this collection
 * @author Ryan Strauss
 */
public interface Collection<E> extends Iterable<E> {

    /**
     * Ensures that this collection contains the specified element (optional operation). Returns true if this
     * collection changed as a result of the call. (Returns false if this collection does not permit duplicates
     * and already contains the specified element.)
     *
     * @param e element whose presence in this collection is to be ensured
     * @return true if this collection changed as a result of the call
     */
    boolean add(E e);

    /**
     * Adds all of the elements in the specified collection to this collection.
     *
     * @param c collection containing elements to be added to this collection
     * @return true if this collection changed as a result of the call
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * Removes all of the elements from this collection. The collection will be empty after this method returns.
     */
    void clear();

    /**
     * Returns true if this collection contains the specified element. More formally, returns true if and only if this
     * collection contains at least one element e such that (o==null ? e==null : o.equals(e)).
     *
     * @param o element whose presence in this collection is to be tested
     * @return true if this collection contains the specified element
     */
    boolean contains(Object o);

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return true if this collection contains all of the elements in the specified collection
     */
    boolean containsAll(Collection<?> c);

    /**
     * Returns true if this collection contains no elements.
     *
     * @return true if this collection contains no elements
     */
    boolean isEmpty();

    /**
     * Returns an iterator over the elements in this collection. There are no guarantees concerning the order in which
     * the elements are returned (unless this collection is an instance of some class that provides a guarantee).
     *
     * @return an Iterator over the elements in this collection
     */
    Iterator<E> iterator();

    /**
     * Removes a single instance of the specified element from this collection, if it is present.
     * More formally, removes an element e such that (o==null ? e==null : o.equals(e)), if this collection contains
     * one or more such elements. Returns true if this collection contained the specified element (or equivalently,
     * if this collection changed as a result of the call).
     *
     * @param o element to be removed from this collection, if present
     * @return true if an element was removed as a result of this call
     */
    boolean remove(Object o);

    /**
     * Removes all of this collection's elements that are also contained in the specified collection. After this call
     * returns, this collection will contain no elements in common with the specified collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return true if this collection changed as a result of the call
     */
    boolean removeAll(Collection<? extends E> c);

    /**
     * Returns the number of elements in this collection.
     *
     * @return the number of elements in this collection
     */
    int size();

}
