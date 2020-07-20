package search;

/**
 * 简单符号表API
 *
 * @author Shane Tang
 * @create 2020-06-04 21:44
 */
public abstract class SimpleST<Key, Value> {

    abstract void put(Key key, Value value);

    abstract Value get(Key key);

    abstract void delete(Key key);

    boolean contains(Key key) {
        return get(key) != null;
    }

    boolean isEmpty() {
        return size() == 0;
    }

    abstract int size();

    /**
     * 表中所有键的集合
     * @return
     */
    abstract Iterable<Key> keys();
}