package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找符号表（基于有序数组）
 *
 * @author Shane Tang
 * @create 2020-06-11 20:44
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> extends AbstractST<Key, Value> {

    private Key[] keys;

    private Value[] values;

    private int N;

    final int DEFAULT_SIZE = 4;

    public BinarySearchST() {
        keys = (Key[]) new Comparable[DEFAULT_SIZE];
        values = (Value[]) new Object[DEFAULT_SIZE];
    }

    public BinarySearchST(int size) {
        keys = (Key[]) new Comparable[size];
        values = (Value[]) new Object[size];
    }

    @Override
    public void put(Key key, Value val) {
        if (size() + 1 == keys.length) {
            resize(2 * keys.length);
        }
        int rank = rank(key);
        // 如果命中，更新value
        if (rank < size() && keys[rank].compareTo(key) == 0) {
            values[rank] = val;
            return;
        }
        // 如果未命中，插入相应位置
        for (int i = size(); i > rank; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[rank] = key;
        values[rank] = val;
        this.N++;
    }

    private int size() {
        return this.N;
    }

    private void resize(int length) {
//        Key[] newkeys = (Key[]) new Object[length];
        keys = Arrays.copyOf(keys, length);
        values = Arrays.copyOf(values, length);
//        keys = newKeys;
//        values = newValues;
    }

    @Override
    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int rank = rank(key);
        if (rank < size() && keys[rank].compareTo(key) == 0) {
            return values[rank];
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return this.N == 0;
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[size() - 1];
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return keys[rank(key)];
    }

    @Override
    public int rank(Key key) {
        int lo = 0;
        // 注意不是keys.length - 1
        int hi = size() - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
    }


    @Override
    public Key select(int k) {
        return keys[k];
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> res = new ArrayList<>(Arrays.asList(keys).subList(rank(lo), rank(hi)));
        if (contains(hi)) {
            res.add(keys[rank(hi)]);
        }
        return res;
    }
}
