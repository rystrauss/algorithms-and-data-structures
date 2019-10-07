package collections;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Resizable-array implementation of the List interface.
 *
 * @param <E> the type of the elements in the list
 * @author Ryan Strauss
 */
public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] data;
    private int size;

    /**
     * Grows the underlying array by 50%.
     */
    private void grow() {
        this.data = Arrays.copyOf(this.data, (int) (this.data.length * 1.5));
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) this.data[index];
    }

    /**
     * Constructs an ArrayList with an initial capacity of 10.
     */
    public ArrayList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public boolean add(E object) {
        if (this.size == this.data.length)
            grow();

        this.data[this.size++] = object;
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
        for (int i = 0; i < this.size; i++)
            this.data[i] = null;

        this.size = 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (o.equals(this.data[i]))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o))
                return false;
        }

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (this.size == this.data.length)
            grow();

        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);

        this.data[index] = element;
        this.size++;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        while (this.data.length < this.size + c.size())
            grow();

        System.arraycopy(this.data, index, this.data, index + c.size(), this.size - index);

        for (E e : c)
            this.data[index++] = e;

        this.size += c.size();
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        return elementData(index);
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        E old = elementData(index);
        this.data[index] = element;

        return old;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size)
            throw new IndexOutOfBoundsException();

        E removed = elementData(index);
        System.arraycopy(this.data, index + 1, this.data, index, this.size - index - 1);
        this.size--;

        return removed;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = this.size - 2; i >= 0; i--) {
            if (this.data[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<? extends E> c) {
        boolean wasChanged = false;

        for (E e : c)
            wasChanged |= remove(e);

        return wasChanged;
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
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E> {

        private int pos;

        ArrayListIterator() {
            this.pos = 0;
        }

        public boolean hasNext() {
            return this.pos < size;
        }

        public E next() {
            return elementData(this.pos++);
        }

    }

}
