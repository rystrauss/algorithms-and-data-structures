package list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Implementation of a list that relies on an array to store data.
 *
 * @param <T> the type of the elements in the list
 * @author Ryan Strauss
 */
public class ArrayList<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int size;

    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(T object) {
        if (this.size == this.data.length)
            grow();

        this.data[this.size++] = object;
    }

    @Override
    public void add(T object, int index) {
        if (this.size == this.data.length)
            grow();

        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);

        this.data[index] = object;
        this.size++;
    }

    /**
     * Grows the underlying array by 50%.
     */
    private void grow() {
        this.data = Arrays.copyOf(this.data, (int) (this.data.length * 1.5));
    }

    @SuppressWarnings("unchecked")
    private T elementData(int index) {
        return (T) this.data[index];
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        return elementData(index);
    }

    @Override
    public T set(T element, int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        T old = elementData(index);
        this.data[index] = element;

        return old;
    }

    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        T removed = elementData(index);
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
        this.size--;

        return removed;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(element))
                return i;
        }
        return -1;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
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
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < this.size - 1; i++)
            sb.append(this.data[i]).append(", ");
        sb.append(this.data[this.size - 1]).append("]");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T> {

        private int pos;

        ArrayListIterator() {
            this.pos = 0;
        }

        public boolean hasNext() {
            return this.pos < size;
        }

        public T next() {
            return elementData(this.pos++);
        }

    }

}
