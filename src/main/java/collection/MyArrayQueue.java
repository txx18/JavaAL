package collection;

import org.omg.CORBA.Object;

/**
 * @author Shane Tang
 * @create 2020-07-11 9:45
 */
public class MyArrayQueue<T> implements MyQueue<T> {

    private T[] elements;

    private int size;

    public MyArrayQueue() {
        elements = ((T[]) new Object[4]);
    }

    private void ensureCapacity(int newCap) {
        T[] newArr = (T[]) new Object[newCap];
        for (int i = 0; i < size(); i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(T x) {
        if (size == elements.length) {
            ensureCapacity(size * 2 + 1);
        }
        elements[size] = x;
        size++;
    }

    @Override
    public T deque() {
        return null;
    }
}
