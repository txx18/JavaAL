package pq;

import zhelper.ArrayUtils;

import static zhelper.ArrayUtils.*;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-05-06 21:48
 */
public interface PQ<Key extends Comparable<Key>> { // 填充范型，根据Key比较
    /**
     * 自底向上堆有序化（上浮）
     * @param k
     */
    void swim(int k);

    /**
     * 自顶向下堆有序化（下沉）
     * @param k
     */
    void sink(int k);

    /**
     * 插入一个元素
     *
     * @param v
     */
    void insert(Key v);

    /**
     * 返回堆顶元素
     *
     * @return
     */
    Key root();

    /**
     * 删除并返回堆顶元素
     *
     * @return
     */
    Key delRoot();

    boolean isEmpty();

    int size();

/*    default boolean less(Key[] pq, int i, int j) {
        return ArrayUtils.less(pq, i, j);
    }*/

/*    default void swap(Key[] pq, int i, int j) {
        ArrayUtils.swap(pq, i, j);
    }*/
}
