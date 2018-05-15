package lists;

/**
 * Implementation of a singly linked list of integers.
 *
 * @author Ryan Staruss
 */
public class LinkedList implements List {

    private class Node {
        private int value;
        private Node next;

        private Node(int value) {
            this.value = value;
            next = null;
        }
    }

    private Node head, tail;
    private int size;

    /**
     * Constructs an empty <code>LinkedList</code>.
     */
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds the given integer to the end of the list.
     *
     * @param n the number to be added
     */
    public void add(int n) {
        Node newNode = new Node(n);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds the given integer to the list at the given index.
     *
     * @param n the number to be added
     * @param index the location at which to add n
     */
    public void add(int n, int index) {
        if (index < 0 | index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(n);
            return;
        }
        Node newNode = new Node(n);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node curr = head;
            int location = 0;
            while (curr.next != null && location++ < index - 1) {
                curr = curr.next;
            }
            Node temp = curr.next;
            curr.next = newNode;
            newNode.next = temp;
        }
        size++;
    }

    /**
     * Gets the element at the specified index.
     *
     * @param index the index of the desired element
     * @return the element at index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node pos = head;
        for (int i = 0; i < index; i++) {
            pos = pos.next;
        }
        return pos.value;
    }

    /**
     * Removes the element at the specified index, and returns that element.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public int remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node pos = head;
        Node temp;
        for (int i = 0; i < index - 1; i++) {
            pos = pos.next;
        }
        temp = pos.next;
        pos.next = pos.next.next;
        return temp.value;
    }

    /**
     * Gets the size of the list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns boolean value of whether or not the list is empty.
     *
     * @return true iff the list has at least one element.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the given integer to a sorted list such that ascending order is maintained.
     *
     * @param n the number to be added
     */
    public void sortedInsert(int n) {
        Node newNode = new Node(n);
        if (head == null || head.value >= n) {
            newNode.next = head;
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null && curr.next.value < n) {
                curr = curr.next;
            }
            Node temp = curr.next;
            curr.next = newNode;
            newNode.next = temp;
        }
        size++;
    }

    /**
     * @return A string representation of this <code>LinkedList</code>.
     */
    @Override
    public String toString() {
        if (head == null)
            return "[]";
        String s = "[";
        Node curr = head;
        while (curr.next != null) {
            s += curr.value + ", ";
            curr = curr.next;
        }
        s += curr.value + "]";
        return s;
    }

}
