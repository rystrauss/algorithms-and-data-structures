package heap;

import java.util.ArrayList;

/**
 * Implementation of a max-heap.
 *
 * @author Ryan Strauss
 */
public class MaxHeap<T extends Comparable<T>> implements Heap<T> {

    private ArrayList<T> nodes;

    public MaxHeap() {
        nodes = new ArrayList<>();
    }

    /**
     * Given a position i, return the position of the parent node.
     *
     * @param i Position to check for the parent node.
     * @return Position of the parent node.
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * Given a position i, return the position of the left child.
     *
     * @param i Position to check for the left child.
     * @return Position of the left child.
     */
    private int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * Given a position i, return the position of the right child.
     *
     * @param i Position to check for the right child.
     * @return Position of the right child.
     */
    private int rightChild(int i) {
        return 2 * i + 2;
    }

    /**
     * Swap elements at positions pos1 and pos2.
     *
     * @param pos1 First position.
     * @param pos2 Second position (no order implied).
     */
    private void swap(int pos1, int pos2) {
        T temporary = nodes.get(pos1);
        nodes.set(pos1, nodes.get(pos2));
        nodes.set(pos2, temporary);
    }

    @Override
    public void push(T value) {
        nodes.add(value);
        siftUp();
    }


    @Override
    public T pop() {
        T removed = nodes.get(0);
        T last = nodes.remove(nodes.size() - 1);
        if (nodes.size() == 0)
            return removed;
        nodes.set(0, last);
        siftDown();
        return removed;
    }

    @Override
    public T peek() {
        return nodes.get(0);
    }

    @Override
    public T replace(T value) {
        T removed = nodes.get(0);
        nodes.set(0, value);
        siftDown();
        return removed;
    }

    /**
     * Balancing operation for when a new element is added to the root of the heap.
     */
    private void siftDown() {
        int i = 0;
        while (true) {
            int swap = i;
            if (leftChild(i) < nodes.size() && nodes.get(leftChild(i)).compareTo(nodes.get(swap)) > 0) {
                swap = leftChild(i);
            }
            if (rightChild(i) < nodes.size() && nodes.get(rightChild(i)).compareTo(nodes.get(swap)) > 0) {
                swap = rightChild(i);
            }
            if (swap == i) {
                break;
            } else {
                swap(swap, i);
                i = swap;
            }
        }
    }

    /**
     * Balancing operation for when a new element is added to the end of the heap.
     */
    private void siftUp() {
        int i = nodes.size() - 1;
        while (i > 0 && nodes.get(parent(i)).compareTo(nodes.get(i)) < 0) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    @Override
    public int size() {
        return nodes.size();
    }

    @Override
    public boolean isEmpty() {
        return nodes.size() == 0;
    }
}
