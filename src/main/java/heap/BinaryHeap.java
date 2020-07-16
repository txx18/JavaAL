package heap;

import sun.dc.pr.PRError;

/**
 * 二叉堆
 *
 * @author Shane Tang
 * @create 2020-07-16 16:56
 */
public class BinaryHeap<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] elements;

    private int size;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {

    }

    public void insert(T x) {

    }

    public T findMin() {
        return null;
    }

    public T deleteMin() {
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        for (T element : elements) {
            element = null;
        }
    }

    private void swim(int hole) {

    }

    private void buildHeap() {

    }

    private void enlargeArray(int newSize) {

    }
}

