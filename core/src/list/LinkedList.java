package list;

import java.util.Iterator;

/**
 * Implementation of a simple singly linked list.
 *
 * @author Ryan Strauss
 */
public class LinkedList<T> implements List<T> {

    private class Node {

        private T value;
        private Node next;

        private Node(T value) {
            this.value = value;
            next = null;
        }

    }

    private Node head, tail;
    private int size;

    /**
     * Constructs an empty list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T object) {
        Node newNode = new Node(object);
        if (this.head == null) {
            this.head = newNode;
            this.tail = this.head;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.size++;
    }

    @Override
    public void add(T object, int index) {
        if (index < 0 | index > this.size)
            throw new IndexOutOfBoundsException();

        if (index == this.size) {
            add(object);
            return;
        }

        Node newNode = new Node(object);

        if (index == 0) {
            newNode.next = this.head;
            this.head = newNode;
        } else {
            Node curr = this.head;
            int location = 0;
            while (curr.next != null && location++ < index - 1) {
                curr = curr.next;
            }
            Node temp = curr.next;
            curr.next = newNode;
            newNode.next = temp;
        }
        this.size++;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        Node pos = this.head;
        for (int i = 0; i < index; i++) {
            pos = pos.next;
        }
        return pos.value;
    }

    @Override
    public T set(T element, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        Node cur = this.head;
        while (--index >= 0)
            cur = cur.next;

        T oldValue = cur.value;
        cur.value = element;

        return oldValue;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();
        Node pos = this.head;
        Node temp;
        for (int i = 0; i < index - 1; i++) {
            pos = pos.next;
        }
        temp = pos.next;
        pos.next = pos.next.next;
        return temp.value;
    }

    @Override
    public int indexOf(T element) {
        int index = 0;
        Node cur = this.head;
        while (cur != null) {
            if (cur.value.equals(element))
                return index;
            cur = cur.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean remove(T element) {
        Node cur = this.head;

        while (cur.next != null) {
            if (cur.next.value.equals(element)) {
                cur.next = cur.next.next;
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    /**
     * Reverses the elements of the list.
     * <p>
     * This implementation runs in O(n) time.
     */
    public void reverse() {
        Node prev = null;
        Node next = null;
        Node cur = this.head;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        this.tail = this.head;
        this.head = prev;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        if (this.head == null)
            return "[]";
        StringBuilder s = new StringBuilder("[");
        Node curr = this.head;
        while (curr.next != null) {
            s.append(curr.value).append(", ");
            curr = curr.next;
        }
        s.append(curr.value).append("]");
        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node nextNode;

        LinkedListIterator() {
            this.nextNode = head;
        }

        public boolean hasNext() {
            return this.nextNode != null;
        }

        public T next() {
            T data = this.nextNode.value;
            this.nextNode = this.nextNode.next;
            return data;
        }

    }

}
