package heap;

import sun.dc.pr.PRError;

/**
 * 二叉堆
 *
 * @author Shane Tang
 * @create 2020-07-16 16:56
 */
public class BinaryHeap<T extends Comparable<? super T>> {

    private static final int DEFAULT_CAPACITY = 10;

    private T[] elements;

    private int size;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        elements = (T[])new Object[capacity];
        size = 0;
    }

    public void insert(T x) {
        if (size == elements.length - 1) {
            ensureCapacity(size * 2 + 1) ;
        }
        int insertIndex = size + 1;
        // TODO elements[0] = x
        for (elements[0] = x; x.compareTo(elements[insertIndex / 2]) < 0; insertIndex /= 2) {
            elements[insertIndex] = elements[insertIndex / 2];
        }
        elements[insertIndex] = x;
        size++;
    }

    private void swap(T[] elements, int i, int j) {
        T t = elements[i];
        elements[i] = elements[j];
        elements[j] = t;
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

    private void ensureCapacity(int newSize) {
        T[] old = elements;
        elements = (T[]) new Object[newSize];
        for (int i = 0; i < old.length; i++) {
            elements[i] = old[i];
        }
    }
}

