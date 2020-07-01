package search;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Objects;

/**
 * @author Shane Tang
 * @create 2020-07-01 15:21
 */
public class LinearProbingHashST<Key, Value> extends SimpleST<Key, Value> {

    private Key[] keys;

    private Value[] vals;
    /**
     * 元素个数N（一般不满且大概是半满的状态）
     */
    private int N;
    /**
     * 底层数组大小M
     */
    private int M;

    public LinearProbingHashST(int M) {
        this.keys = (Key[]) new Object[M];
        this.vals = ((Value[]) new Object[M]);
        this.M = M;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % this.M;
    }

    @Override
    void put(Key key, Value value) {
        // 扩容操作
        if (this.N >= this.M / 2) {
            resize(2 * M);
        }
        // 计算hash值，计算索引
        int index = hash(key);
/*
        while (keys[index] != null && !key.equals(keys[index]) && index < this.M) {
            index++;
        }
        // 如果该位置是空的，则插入
        if (keys[index] == null) {
            keys[index] = key;
            vals[index] = value;
            this.N++;
            return;
        }
        // 如果该位置原先有Key而且相同，则更新
        if (key.equals(keys[index])) {
            keys[index] = key;
        }*/
        int i;
        // 如果该位置原先有Key但不同，则往后探测，直到找到空的或者相同的
        for (i = index; keys[i] != null; i = (i + 1) % this.M) {
            // 如果该位置原先有Key而且相同，则更新
            if (key.equals(keys[i])) {
                vals[i] = value;
                return;
            }
        }
        // 如果该位置是空的，则插入，这里i已经移到位了
        keys[i] = key;
        vals[i] = value;
        this.N++;
    }

    private void resize(int cap) {
        LinearProbingHashST<Key, Value> t = new LinearProbingHashST<>(cap);
        // 将原表中的所有键重新散列并插入到新表中
        for (int i = 0; i < this.M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        this.keys = t.keys;
        this.vals = t.vals;
        this.M = t.M;
    }

    @Override
    Value get(Key key) {
/*        int index = hash(key);
        while (keys[index] != null && !key.equals(keys[index]) && index < this.M) {
            index++;
        }
        if (index == this.M) {
            return null;
        }
        return vals[index];*/
        for (int i = hash(key); keys[i] != null ; i = (i + 1) % this.M) {
            if (key.equals(keys[i])) {
                return vals[i];
            }
        }
        return null;
    }

    @Override
    void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            // 将簇中被删除键的右侧所有键重新插入散列表，
            this.N--;
            put(keyToRedo, valToRedo);
            i = (i + 1 ) % M;
        }
        this.N--;
        if (this.N > 0 && this.N <= this.M / 8) {
            resize(M / 2);
        }
    }

    @Override
    int size() {
        return this.N;
    }

    @Override
    Iterable<Key> keys() {
        return null;
    }
}
