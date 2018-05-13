package lists;

/**
 * Implementation of a singly linked list of integers.
 *
 * @author Ryan Staruss
 */
public class LinkedList {

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
