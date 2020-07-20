package pq;

import static zhelper.ArrayUtils.less;
import static zhelper.ArrayUtils.swap;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-05-06 22:09
 */
public class MaxPQ<Key extends Comparable<Key>> extends PQ<Key> { // <Key extends Comparable<Key>>

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<Integer>(10);
        maxPQ.insert(4);
        maxPQ.insert(2);
        maxPQ.insert(8);
        maxPQ.insert(1);
        System.out.println(maxPQ.delRoot());
    }


    public MaxPQ(int maxN) {
        super(maxN);
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
     *
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

}
