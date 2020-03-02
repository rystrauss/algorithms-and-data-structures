package com.rystrauss.collections;

import java.util.Iterator;

/**
 * Doubly-linked list implementation of the List and Deque interfaces.
 *
 * @param <E> the type of elements held in this collection
 * @author Ryan Strauss
 */
public class LinkedList<E> implements List<E>, Deque<E> {

    private class Node {

        Node prev, next;
        E data;

        Node(E data) {
            this(data, null, null);
        }

        Node(E data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }

    private Node head, tail;
    private int size;

    /**
     * Gets the node at the given index in the list.
     * <p>
     * Depending on the index, this method will begin at whichever end of the list allows it to
     * most quickly reach the desired node.
     *
     * @param index the index of the node to be returned
     * @return the node at the specified index
     */
    private Node getNode(int index) {
        Node cur;

        if (index < this.size / 2) {
            cur = this.head;
            while (--index >= 0)
                cur = cur.next;
        } else {
            cur = this.tail;
            while (++index < this.size)
                cur = cur.prev;
        }

        return cur;
    }

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        Node cur = getNode(index);

        Node newNode = new Node(element, cur.prev, cur);
        if (cur.prev != null) {
            cur.prev.next = newNode;
        }
        cur.prev = newNode;

        if (newNode.prev == null)
            this.head = newNode;

        this.size++;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        Node cur = getNode(index);
        Node newNode;

        for (E e : c) {
            newNode = new Node(e, cur.prev, cur);
            if (cur.prev != null) {
                cur.prev.next = newNode;
            }
            cur.prev = newNode;

            if (newNode.prev == null)
                this.head = newNode;
        }

        this.size += c.size();
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        Node cur = getNode(index);

        return cur.data;
    }

    @Override
    public int indexOf(Object o) {
        Node cur = this.head;
        int index = 0;
        while (cur != null) {
            if (cur.data.equals(o))
                return index;
            cur = cur.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node cur = this.tail;
        int index = this.size - 1;
        while (cur != null) {
            if (cur.data.equals(o))
                return index;
            cur = cur.prev;
            index--;
        }
        return -1;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        E data;

        if (index == 0) {
            data = this.head.data;
            this.head.next.prev = null;
            this.head = this.head.next;
        } else if (index == this.size - 1) {
            data = this.tail.data;
            this.tail.prev.next = null;
            this.tail = this.tail.prev;
        } else {
            Node cur = getNode(index);
            data = cur.data;
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }

        this.size--;
        return data;
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        Node cur = getNode(index);
        E oldData = cur.data;
        cur.data = element;
        return oldData;
    }

    @Override
    public boolean add(E e) {
        if (this.head == null) {
            this.head = new Node(e);
            this.tail = this.head;
        } else {
            Node node = new Node(e, this.tail, null);
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e : c)
            add(e);
        return true;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public boolean contains(Object o) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.data.equals(o))
                return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = false;
        for (Object o : c)
            result |= contains(o);
        return result;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public boolean remove(Object o) {
        Node cur = this.head;
        while (cur != null) {
            if (cur.data.equals(o)) {
                if (cur == this.head) {
                    this.head.next.prev = null;
                    this.head = this.head.next;
                } else if (cur == this.tail) {
                    this.tail.prev.next = null;
                    this.tail = this.tail.prev;
                } else {
                    cur.prev.next = cur.next;
                    cur.next.prev = cur.prev;
                }
                this.size--;
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public E pollFirst() {
        return remove(0);
    }

    @Override
    public E peekFirst() {
        return this.head.data;
    }

    @Override
    public void addLast(E e) {
        add(e);
    }

    @Override
    public E pollLast() {
        return remove(this.size - 1);
    }

    @Override
    public E peekLast() {
        return this.tail.data;
    }

    @Override
    public E peek() {
        return this.head.data;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node cur = this.head;

        while (cur.next != null) {
            sb.append(cur.data).append(", ");
            cur = cur.next;
        }
        sb.append(cur.data).append("]");

        return sb.toString();
    }

    private class LinkedListIterator implements Iterator<E> {

        private Node nextNode;

        LinkedListIterator() {
            this.nextNode = head;
        }

        @Override
        public boolean hasNext() {
            return this.nextNode != null;
        }

        @Override
        public E next() {
            E data = this.nextNode.data;
            this.nextNode = this.nextNode.next;
            return data;
        }

    }

}
