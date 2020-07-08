package collection;

import java.util.Iterator;

/**
 * @author Shane Tang
 * @create 2020-07-07 16:01
 */
public class MyArrayList<T> implements Iterable<T> {

    private int size;

    private T[] items;

    public MyArrayList() {
        this.items = ((T[]) new Object[10]);
    }

    public int size() {
        return size;
    }

    // get
    public T get(int index) {
        if (index >= size || index < 0 ) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[index];
    }

    // set
    public void set(int index, T newItem) {
        if (index >= size || index < 0 ) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        items[index] = newItem;
    }

    // ensureCapacity
    private void ensureCapacity(int newCap) {

    }

    // add
    public void add(T x) {

    }

    public void add(int index, T x) {

    }



    // remove
    public T removeItem(int index) {
        return null;
    }


    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(items[i]).append(" ");
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
            return items[cur++];
        }

        @Override
        public void remove() {

        }
    }
}
