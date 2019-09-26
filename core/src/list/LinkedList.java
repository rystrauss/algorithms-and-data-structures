package list;

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
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T object) {
        Node newNode = new Node(object);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(T object, int index) {
        if (index < 0 | index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(object);
            return;
        }
        Node newNode = new Node(object);
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

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node pos = head;
        for (int i = 0; i < index; i++) {
            pos = pos.next;
        }
        return pos.value;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
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

    @Override
    public void reverse() {
        Node prev = null;
        Node next = null;
        Node cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        tail = head;
        head = prev;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (head == null)
            return "[]";
        StringBuilder s = new StringBuilder("[");
        Node curr = head;
        while (curr.next != null) {
            s.append(curr.value).append(", ");
            curr = curr.next;
        }
        s.append(curr.value).append("]");
        return s.toString();
    }

}
