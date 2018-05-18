package heaps;

public interface Heap {

    /**
     * Adds the given element to the heap.
     *
     * @param value the element to be added
     */
    void push(int value);

    /**
     * Removes and returns the head of the heap.
     *
     * @return the value stored at the head of the heap
     */
    int pop();

    /**
     * Retrieves the values stored at the head of the heap.
     *
     * @return the value stored at the head of the heap
     */
    int peek();

    /**
     * Pop root and push a new key.
     *
     * @param value new element to be added
     */
    int replace(int value);

    /**
     * Gets the size of the heap.
     *
     * @return the number of elements in the heap
     */
    int size();

    /**
     * Determines whether or not the heap is empty.
     *
     * @return true iff the heap is empty
     */
    boolean isEmpty();

}
