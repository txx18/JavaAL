package collection.imitJdk;


import java.util.Iterator;

/**
 * @author Shane Tang
 * @create 2020-01-30 12:05
 */
public class MyVector<E> extends MyAbstractList<E> implements Iterable<E>{

    protected Object[] elementData;
    protected int size;
    protected int capacityIncrement;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public MyVector(int initialCapacity, int capacityIncrement) {
        super();
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }


    public MyVector(int initialCapacity) {
        this(initialCapacity, 0);
    }

    public MyVector() {
        this(10);
    }

    public synchronized void add(E e) {
        modCount++;
        ensureCapacity(this.size + 1);
        this.elementData[this.size++] = e;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > this.elementData.length) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = this.elementData.length;
        // 扩容2倍
        int newCapacity = oldCapacity + ((this.capacityIncrement > 0) ?
                this.capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        this.elementData = MyArrays.copyOf(this.elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
        {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    public int size() {
        return this.size;
    }

    public E get(int idx) {
        if (idx >= size) {
            throw new ArrayIndexOutOfBoundsException(idx + " >= " + size);
        }
        return (E) elementData[idx];
    }

    public synchronized E remove(int idx) {
        rangeCheck(idx);
        modCount++;
        int numToMove = size - idx - 1;
        if (numToMove > 0) {
            System.arraycopy(this.elementData, idx + 1, this.elementData, idx, numToMove);
        }
        this.elementData[--size] = null;
        return (E) this.elementData[idx];
    }

    private void rangeCheck(int idx) {
        if (idx >= size) {
            throw new IndexOutOfBoundsException("Index: " + idx + ", Size: " + this.size);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
