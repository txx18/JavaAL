package heap;

import java.util.Comparator;
import java.util.Random;

/**
 * 底层数组从0位置开始
 *
 * @author Shane Tang
 * @create 2020-07-21 21:20
 */
public class MyPriorityQueue<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (new Random().nextInt(10));
        }
        // 构造降序的大根堆
/*        MyPriorityQueue<Integer> bigHeap = new MyPriorityQueue<>(arr, new ComparatorDESC<Integer>());
        System.out.println("heap = " + bigHeap);
        for (int i = 0; i < 10; i++) {
            System.out.println(bigHeap.remove());
            System.out.println("heap = " + bigHeap);
        }*/

        // 构造升序的小根堆
        MyPriorityQueue<Integer> smallHeap = new MyPriorityQueue<>(arr, new ComparatorASC<>());
        System.out.println("heap = " + smallHeap);
        for (int i = 9; i >= 0; i--) {
            System.out.println(smallHeap.remove());
            System.out.println("heap = " + smallHeap);
        }
    }

    private T[] elements;

    private int size;

    private Comparator<T> bigSmallRootComparator;

    public static class ComparatorASC<T extends Comparable<? super T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    private static class ComparatorDESC<T extends Comparable<? super T>> implements Comparator<T> {

        @Override
        public int compare(T o1, T o2) {
            return o2.compareTo(o1);
        }
    }

    /**
     * @param a
     * @param cmp 小根堆0位置是最小的，大根堆0位置是最大的
     */
    public MyPriorityQueue(T[] a, Comparator<T> cmp) {
        this.size = a.length;
        this.elements = (T[]) new Comparable[(size + 2) * 11 / 10];
        // 拷贝
        for (int i = 0; i < size; i++) {
            elements[i] = a[i];
        }
        this.bigSmallRootComparator = cmp;
        // 堆化
        buildheap();
    }

    private void buildheap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            sink(i);
        }
    }

    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T oldRoot = element();
        elements[0] = elements[size - 1];
        sink(0);
        size--;
        return oldRoot;
    }

    private T element() {
        if (isEmpty()) {
            return null;
        }
        return elements[0];
    }

    private boolean isEmpty() {
        return size == 0;
    }


    private void sink(int hole) {
        // 记住待填入元素
        T x = elements[hole];
        int left = hole * 2 + 1;
        int right = left + 1;
        while (true) {
            // 无左无右
            if (left > size - 1) {
                break;
            }
            // 有左无右
            else if (right > size - 1) {
                if (bigSmallRootComparator.compare(x, elements[left]) < 0) {
                    break;
                } else {
                    elements[hole] = elements[left];
                    hole = left;
                    left = hole * 2 + 1;
                    right = left + 1;
                }
            }
            // 有左有右
            else {
                // 取左右孩子较小or大的
                int cmpChild = (bigSmallRootComparator.compare(elements[left], elements[right]) < 0) ? left : right;
                // 和待填入的比较，如果待填入的小or大，直接可以填入，返回
                if (bigSmallRootComparator.compare(x, elements[cmpChild]) < 0) {
                    break;
                } else {
                    elements[hole] = elements[cmpChild];
                    hole = cmpChild;
                    left = hole * 2 + 1;
                    right = left + 1;
                }
            }
        }
        elements[hole] = x;
    }


    @Override
    public String toString() {
        if (isEmpty()) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(" ");
        }
        return sb.toString();
    }
}
