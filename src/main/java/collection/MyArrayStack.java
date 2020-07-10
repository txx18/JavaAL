package collection;

import java.util.EmptyStackException;

/**
 * @author Shane Tang
 * @create 2020-07-10 20:52
 */
public class MyArrayStack<T> {

    private int size;

    private T[] elements;

    public MyArrayStack() {
        elements = ((T[]) new Object[4]);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // ensureCapacity
    private void ensureCapacity(int newCap) {
        T[] newArr = (T[]) new Object[newCap];
        for (int i = 0; i < size(); i++) {
            newArr[i] = elements[i];
        }
        elements = newArr;
    }

    public void push(T x) {
        if (size() == elements.length) {
            ensureCapacity(2 * size + 1);
        }
        elements[size] = x;
        size++;
    }

    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T top = elements[size - 1];
        size--;
        return top;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(elements[i]).append(" ");
        }
        return sb.toString();
    }
}
