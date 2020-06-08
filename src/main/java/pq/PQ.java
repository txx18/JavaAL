package pq;

import zhelper.ArrayUtils;

import static zhelper.ArrayUtils.*;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-05-06 21:48
 */
public abstract class PQ<Key extends Comparable<Key>> { // 填充范型，根据Key比较

    public Key[] pq;

    public int heapSize = 0;

    public PQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * 自底向上堆有序化（上浮）
     *
     * @param k
     */
    abstract void swim(int k);

    /**
     * 自顶向下堆有序化（下沉）
     *
     * @param k
     */
    abstract void sink(int k);

    /**
     * 插入一个元素
     *
     * @param v
     */
    void insert(Key v) {
        // 存储于pq[1..N]中，pq[0]没有使用
        pq[++heapSize] = v;
        swim(heapSize);
    }

    /**
     * 返回堆顶元素
     *
     * @return
     */
    Key root() {
        return pq[1];
    }

    /**
     * 删除并返回堆顶元素
     *
     * @return
     */
    Key delRoot() {
        // 从根节点获得最大元素
        Key root = root();
        // 和最后一个节点交换
        swap(pq, 1, heapSize--);
        // 防止越界
        pq[heapSize + 1] = null;
        // 恢复堆的有序性
        sink(1);
        return root;
    }

    boolean isEmpty() {
        return heapSize == 0;
    }

    int size() {
        return heapSize;
    }

    protected boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }

    protected void swap(Key[] a, int i, int j) {
        Key t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
