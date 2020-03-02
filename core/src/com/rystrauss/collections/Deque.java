package com.rystrauss.collections;

/**
 * A linear collection that supports element insertion and removal at both ends. The name deque is short for
 * "double ended queue" and is usually pronounced "deck".
 *
 * @param <E> the type of elements held in this collection
 * @author Ryan Strauss
 */
public interface Deque<E> extends Queue<E> {

    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param e the element to add
     */
    void addFirst(E e);

    /**
     * Retrieves and removes the first element of this deque, or returns null if this deque is empty.
     *
     * @return the head of this deque, or null if this deque is empty
     */
    E pollFirst();

    /**
     * Retrieves, but does not remove, the first element of this deque, or returns null if this deque is empty.
     *
     * @return the head of this deque, or null if this deque is empty
     */
    E peekFirst();

    /**
     * Inserts the specified element at the end of this deque.
     *
     * @param e the element to add
     */
    void addLast(E e);

    /**
     * Retrieves and removes the last element of this deque, or returns null if this deque is empty.
     *
     * @return the tail of this deque, or null if this deque is empty
     */
    E pollLast();

    /**
     * Retrieves, but does not remove, the last element of this deque, or returns null if this deque is empty.
     *
     * @return the tail of this deque, or null if this deque is empty
     */
    E peekLast();

}
