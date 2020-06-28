package search;

/**
 * 简单符号表API
 *
 * @author Shane Tang
 * @create 2020-06-04 21:44
 */
public interface SimpleST<K, V> {
    void put(K k, V v);

    V get(K k);

    default void delete(K k) {
        put(k, null);
    }

    default boolean contains(K k) {
        return get(k) != null;
    }

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    /**
     * 表中所有键的集合
     * @return
     */
    Iterable<K> keys();
}