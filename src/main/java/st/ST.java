package st;

/**
 * 简单符号表API
 *
 * @author Shane Tang
 * @create 2020-06-04 21:44
 */
public interface ST<Key extends Comparable<Key>, V> {

    void put(Key key, V v);

    V get(Key key);

    default void delete(Key key) {
        put(key, null);
    }

    default boolean contains(Key key) {
        return get(key) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    Key min();

    Key max();

    /**
     * <=k的最大键
     * @param key
     * @return
     */
    Key floor(Key key);

    /**
     * >=key的最小键
     * @param key
     * @return
     */
    Key ceiling(Key key);

    /**
     * 小于key的键的数量
     * @param key
     * @return
     */
    int rank(Key key);

    /**
     * 排名为k的的键
     * @param k
     * @return
     */
    Key select(int k);

    /**
     * 删除最小的键
     */
    void deleteMin();

    /**
     * 删除最大的键
     */
    void deleteMax();

    /**
     * [lo..hi]之间键的数量
     * @param lo
     * @param hi
     * @return
     */
    int size(Key lo, Key hi);

    /**
     * 表中所有键的集合
     * @return
     */
    Iterable<Key> keys();

    /**
     * [lo..hi]之间的所有键，已排序
     * @param lo
     * @param hi
     * @return
     */
    Iterable<Key> keys(Key lo, Key hi);
}
