package collections;

/**
 * A collection designed for holding elements prior to processing. Queues typically, but do not necessarily, order
 * elements in a FIFO (first-in-first-out) manner. Among the exceptions are priority queues, which order elements
 * according to their natural ordering.
 *
 * @param <E> the type of elements held in this collection
 * @author Ryan Strauss
 */
public interface Queue<E> extends Collection<E> {

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    E peek();

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    E poll();

}
