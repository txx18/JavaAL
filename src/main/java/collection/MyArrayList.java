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
        this.items = ((T[]) new Object[4]);
    }

    public int size() {
        return size;
    }

    // get
    public T get(int index) {
        if (index >= size || index < 0) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[index];
    }

    // set
    public void set(int index, T newItem) {
        if (index >= size || index < 0) { /*index < 0*/
            throw new ArrayIndexOutOfBoundsException();
        }
        items[index] = newItem;
    }

    // ensureCapacity
    private void ensureCapacity(int newCap) {
        T[] newArr = (T[]) new Object[newCap];
        for (int i = 0; i < size(); i++) {
            newArr[i] = items[i];
        }
        items = newArr;
    }

    // add
    public void add(T x) {
        add(size(), x);
    }

    public boolean add(int index, T x) {
        if (size() == items.length) {
            ensureCapacity(2 * size + 1);
        }
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = x;
        size++;
        return true;
    }


    // remove
    public T remove(int index) {
        T toRm = items[index];
        for (int i = index; i < size() - 1; i++) {  /* i < size() - 1 */
            items[i] = items[i + 1];
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
            MyArrayList.this.remove(--cur);
        }
    }
}
