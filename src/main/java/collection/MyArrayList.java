package collection;

import java.util.Iterator;

/**
 * @author Shane Tang
 * @create 2020-07-07 16:01
 */
public class MyArrayList<T> implements Iterable<T>, MyList<T> {

    private int size;

    private T[] elements;

    public MyArrayList() {
        this.elements = ((T[]) new Object[4]);
    }

    @Override
    public int size() {
        return size;
    }

    // get
    @Override
    public T get(int index) {
        if (index >= size || index < 0) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[index];
    }

    // set
    @Override
    public T set(int index, T newItem) {
        T old = elements[index];
        if (index >= size || index < 0) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        elements[index] = newItem;
        return old;
    }

    // ensureCapacity
    private void ensureCapacity(int newCap) {
        T[] newArr = (T[]) new Object[newCap];
        for (int i = 0; i < size(); i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
    }

    // add
    @Override
    public void add(T x) {
        add(size(), x);
    }

    public boolean add(int index, T x) {
        if (size() == elements.length) {
            ensureCapacity(2 * size + 1);
        }
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = x;
        size++;
        return true;
    }


    // remove
    @Override
    public T remove(int index) {
        T toRm = elements[index];
        for (int i = index; i < size() - 1; i++) {  /* i < size() - 1 */
            elements[i] = elements[i + 1];
        }
        size--;
        return toRm;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(elements[i]).append(" ");
        }
        return sb.toString();
    }

    private class MyArrayListIterator implements Iterator<T> {

        int cur = 0;

        @Override
        public boolean hasNext() {
            return cur < size();
        }

        @Override
        public T next() {
            return elements[cur++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--cur);
        }
    }
}
