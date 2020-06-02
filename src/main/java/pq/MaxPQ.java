package pq;

import static zhelper.ArrayUtils.less;
import static zhelper.ArrayUtils.swap;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-05-06 22:09
 */
public class MaxPQ<Key extends Comparable<Key>> implements PQ<Key> { // <Key extends Comparable<Key>>

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<Integer>(10);
        maxPQ.insert(4);
        maxPQ.insert(2);
        maxPQ.insert(8);
        maxPQ.insert(1);
        System.out.println(maxPQ.delRoot());
    }

    private Key[] pq;

    private int heapSize = 0;

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    @Override
    public void insert(Key v) {
        // 存储于pq[1..N]中，pq[0]没有使用
        pq[++heapSize] = v;
        swim(heapSize);
    }

    @Override
    public Key root() {
        return pq[1];
    }

    @Override
    public Key delRoot() {
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

    @Override
    public void swim(int k) {
        while (k > 1 && less(pq[k / 2], pq[k])) {
            swap(pq, k / 2, k);
            k /= 2;
        }
    }

    /**
     * 下沉
     * @param k
     */
    @Override
    public void sink(int k) {
        while (2 * k <= heapSize) {
            // 左孩子
            int j = 2 * k;
            // 左孩子 < 右孩子
            if (j < heapSize && less(pq[j], pq[j + 1])) {
                j++;
            }
            if (!less(pq[k], pq[j])) {
                break;
            }
            swap(pq, k, j);
            k = j;
        }
    }

    @Override
    public boolean isEmpty() {
        return heapSize == 0;
    }

    @Override
    public int size() {
        return heapSize;
    }
}
