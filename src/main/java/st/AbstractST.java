package st;

/**
 * 简单符号表API
 *
 * @author Shane Tang
 * @create 2020-06-04 21:44
 */
public abstract class AbstractST<Key extends Comparable<Key>, Value> {

    public int size;

    abstract void put(Key key, Value val);

    abstract Value get(Key key);

    void delete(Key key) {
        put(key, null);
    }

    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return size;
    }

    abstract Key min();

    abstract Key max();

    /**
     * <=k的最大键
     * @param key
     * @return
     */
    abstract Key floor(Key key);

    /**
     * >=key的最小键
     * @param key
     * @return
     */
    abstract Key ceiling(Key key);

    /**
     * 小于key的键的数量
     * @param key
     * @return
     */
    abstract int rank(Key key);

    /**
     * 排名为k的的键
     * @param k
     * @return
     */
    abstract Key select(int k);

    /**
     * 删除最小的键
     */
    void deleteMin() {
        delete(min());
    }

    /**
     * 删除最大的键
     */
    void deleteMax() {
        delete(max());
    }

    /**
     * [lo..hi]之间键的数量
     *
     * @param lo
     * @param hi
     * @return
     */
    int size(Key lo, Key hi) {
        if (hi.compareTo(lo) < 0) {
            return 0;
        }else if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        }else {
            return rank(hi) - rank(lo);
        }
    }

    /**
     * 表中所有键的集合
     *
     * @return
     */
    Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * [lo..hi]之间的所有键，已排序
     * @param lo
     * @param hi
     * @return
     */
    abstract Iterable<Key> keys(Key lo, Key hi);
}
