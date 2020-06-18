package search.st;

/**
 * 简单符号表API
 *
 * @author Shane Tang
 * @create 2020-06-04 21:44
 */
public abstract class AbstractST<Key extends Comparable<Key>, Value> {

    public int size;

    public abstract void put(Key key, Value val);

    public abstract Value get(Key key);

    public void delete(Key key) {
        // 如果底层是BST就没这么简单了
        put(key, null);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.size;
    }

    public abstract Key min();

    public abstract Key max();

    /**
     * <=key的最大键
     * 向下取整
     * @param key
     * @return
     */
    public abstract Key floor(Key key);

    /**
     * >=key的最小键
     * 向上取整
     * @param key
     * @return
     */
    public abstract Key ceiling(Key key);

    /**
     * 小于key的键的数量
     * @param key
     * @return
     */
    public abstract int rank(Key key);

    /**
     * 排名为k的的键
     * @param k
     * @return
     */
    public abstract Key select(int k);

    /**
     * 删除最小的键
     */
    public void deleteMin() {
        delete(min());
    }

    /**
     * 删除最大的键
     */
    public void deleteMax() {
        delete(max());
    }

    /**
     * [lo..hi]之间键的数量
     *
     * @param lo
     * @param hi
     * @return
     */
    public int size(Key lo, Key hi) {
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
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    /**
     * [lo..hi]之间的所有键，已排序
     * @param lo
     * @param hi
     * @return
     */
    public abstract Iterable<Key> keys(Key lo, Key hi);
}
