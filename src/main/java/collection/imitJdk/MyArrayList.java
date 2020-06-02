package collection.imitJdk;

/**
 * @author Shane Tang
 * @create 2020-01-08 22:06
 */
public class MyArrayList<E> extends MyAbstractList<E> {

    transient Object[] elementData;

    /**
     * 元素个数（不包括List中的null）
     */
    private int size;

    private static final Object[] EMPTY_ELEMENTDATA = {};

    private static final int DEFAULT_CAPACITY = 10;

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    public MyArrayList() {
        this.elementData = EMPTY_ELEMENTDATA;
    }

    /**
     * @param initialCapacity 自定义初始化容量
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        }
        this.elementData = new Object[initialCapacity];
    }

    public boolean add(E e) {
        // 添加1个元素需要保证 size + 1 的容量
        ensureInternalCapacity(this.size + 1);
        this.elementData[this.size++] = e;
        return true;
    }

    /**
     * @param minCapacity 所需最小的容量大小
     */
    private void ensureInternalCapacity(int minCapacity) {
        // 如果目前底层数组是空的，看默认10容量和所需最小容量谁大，就取谁
        if (this.elementData == EMPTY_ELEMENTDATA) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        // 否则目前底层数组不是空的，可能需要扩容
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {
        // 扩容，修改了一次
        modCount++;
        // 如果容量不够，需要扩容
        if (minCapacity > this.elementData.length) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 有可能扩容后还是不够，比如一开始容量为0
        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity > MAX_ARRAY_SIZE) {
            newCapacity = getHugeCapacity(minCapacity);
        }
        this.elementData = MyArrays.copyOf(this.elementData, newCapacity);
    }


    private int getHugeCapacity(int minCapacity) {
        // 溢出
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return minCapacity > MAX_ARRAY_SIZE ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    @Override
    public String toString() {
        return MyArrays.toString(elementData);
    }

    public E get(int index) {
        rangeCheck(index);
        return (E)this.elementData[index];
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
        }
    }

    public E romove(int index) {
        rangeCheck(index);
        modCount++;
        int numToMove = size - index - 1;
        if (numToMove > 0){
            System.arraycopy(this.elementData, index + 1, this.elementData, index, numToMove);
        }
        this.elementData[--size] = null;
        return (E) this.elementData[index];
    }
}
