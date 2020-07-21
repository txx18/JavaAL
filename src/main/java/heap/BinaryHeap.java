package heap;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 二叉堆（一棵完全二叉树，用数组表示）
 * 底层数组从1位置开始
 * @author Shane Tang
 * @create 2020-07-16 16:56
 */
public class BinaryHeap<T extends Comparable<? super T>> {

//    public static void main(String[] args) {
//        BinaryHeap<Integer> heap = new BinaryHeap<>();
//        for (int i = 10; i > 0; i--) {
//            heap.insert(i);
//        }
//        System.out.println(heap);
//        System.out.println(heap.isBinaryHeap());
//        for (int i = 1; i <= 10; i++) {
//            System.out.println(heap.deleteMin());
////            heap.deleteMin();
//            System.out.println(heap);
//        }
//    }

    // Test program
    public static void main(String[] args) {
        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap<>();
        int i = 37;

//        for (i = 37; i != 0; i = (i + 37) % numItems)
//            h.insert(i);
        Integer[] arr = new Integer[numItems - 1];
        int j = 0;
        for (i = 37, j = 0; i != 0; i = (i + 37) % numItems, j++){
            arr[j] = i;
        }
        h = new BinaryHeap<Integer>(arr);
        for (i = 1; i < numItems; i++)
            if (h.deleteMin() != i)
                System.out.println("Oops! " + i);
    }

    private static final int DEFAULT_CAPACITY = 10;

    private T[] elements;

    private int size;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        elements = (T[]) new Comparable[capacity];
        size = 0;
    }

    public BinaryHeap(T[] arr) {
        size = arr.length;
        elements = (T[]) new Comparable[(size + 2) * 11 / 10];
        // 先塞进堆，无序
        int i = 1;
        for (T ele : arr) {
            elements[i++] = ele;
        }
        // 堆化
        buildHeap();
    }

    /**
     * 从集合直接构造堆
     */
    private void buildHeap() {
        for (int i = size / 2; i > 0; i--) {
            sink(i);
        }
    }

    /**
     * 书上写法见book代码
     *
     * 使用空穴的想法而不是交换的想法能减少赋值次数
     *
     * @param x
     */
    public void insert(T x) {
        if (size == elements.length - 1) {
            ensureCapacity(size * 2 + 1);
        }
        // elements[0] = x先将自己放在0位置，以便while堆顶的比较
        elements[0] = x;
        // 空穴一开始设在最后，可以看做里面放了x（由于不用交换方法，所以使这个空穴边上移边覆盖）
        int hole = size + 1;
        elements[hole] = x;
        swim(hole);
        size++;
    }

    /**
     * swim() 空穴hole上滤，直到找到能放【插入元素】的位置
     * 调整堆
     * @param hole
     */
    private void swim(int hole) {
        // 记住待插入元素
        T x = elements[hole];
        // 插入最小值时，hole 来到1位置，循环终止
        int fatherIndex = hole / 2;
        while (x.compareTo(elements[fatherIndex]) < 0) {
            elements[hole] = elements[fatherIndex];
            hole = fatherIndex;
            fatherIndex /= 2;
        }
        elements[hole] = x;
    }

/*    private void swap(T[] elements, int i, int j) {
        T t = elements[i];
        elements[i] = elements[j];
        elements[j] = t;
    }*/

    public T findMin() {
        return elements[1];
    }

    /**
     * 书上写法见book代码
     *
     *
     * @return
     */
    public T deleteMin() {
        if (isEmpty()) {
            return null;
        }
        T oldMin = findMin();
        // 最后一个元素待填入空穴，先把【最后元素】放到1位置
        int hole = 1;
        elements[hole] = elements[size];
        sink(hole);
        size--;
        return oldMin;
    }

    /**
     * 书上写法见book代码（最优雅的写法）
     * sink() 空穴下滤，
     * 堆化时，找到能放依次遍历的值的位置
     * remove后，直到找到能放入【最后元素】的位置
     *
     * @param hole
     */
    private void sink(int hole) {
        // 记住待填入元素（放在1位置）
        T x = elements[hole];
        int left = hole * 2;
        int right = left + 1;
        while (true) {
            // 无左无右
            if (left > size){
                break;
            }
            // 有左无右
            else if (left <= size && right > size) {
                if (x.compareTo(elements[left]) < 0) {
                    break;
                } else {
                    elements[hole] = elements[left];
                    hole = left;
                    left = hole * 2;
                    right = left + 1;
                }
            }
            // 有左有右
            else if (right <= size) {
                // 取左右孩子较小的
                int smallerChildIndex = (elements[left].compareTo(elements[right]) < 0) ? left : right;
                // 和待填入的比较，如果待填入的小，直接可以填入，返回；如果待填入的不够小，空穴下移，循环继续
                if (x.compareTo(elements[smallerChildIndex]) < 0) {
                    break;
                } else {
                    elements[hole] = elements[smallerChildIndex];
                    hole = smallerChildIndex;
                    left = hole * 2;
                    right = left + 1;
                }
            }
        }
        elements[hole] = x;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void makeEmpty() {
        for (T element : elements) {
            element = null;
        }
    }

    private void ensureCapacity(int newSize) {
        T[] old = elements;
        elements = (T[]) new Comparable[newSize];
        for (int i = 0; i < old.length; i++) {
            elements[i] = old[i];
        }
    }

    /**
     * 【注意】堆打印底层数组当然不是有序的，要画成堆的样子
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) { // 打印不是从1开始
            sb.append(elements[i]).append(" ");
        }
        return sb.toString();
    }

    private boolean isBinaryHeap() {
        int cur = 1;
        int left = cur * 2;
        int right = left + 1;
        while (left <= size || right <= size) {
            if (elements[cur].compareTo(elements[left]) > 0
                    && elements[cur].compareTo(elements[right]) > 0) {
                return false;
            }
            cur++;
            left = cur * 2;
            right = left + 1;
        }
        return true;
    }
}

