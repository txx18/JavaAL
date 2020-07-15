package hashtable;

/**
 * 平方探测法
 *
 * @author Shane Tang
 * @create 2020-07-15 22:44
 */
public class QuadraticProbingHashTable<T> {

    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<T>[] entries;

    private int size;

    private class HashEntry<T> {
        private T element;

        private boolean isActive;

        public HashEntry(T e) {
            this(e, true);
        }

        public HashEntry(T e, boolean b) {
            element = e;
            isActive = b;
        }
    }

    public QuadraticProbingHashTable(){
       this(DEFAULT_TABLE_SIZE); // this(成员变量) 必须是static的
    }

    public QuadraticProbingHashTable(int size) {
        entries = new HashEntry[size];
    }

    public void makeEmpty() {
        for (int i = 0; i < entries.length; i++) {
            entries[i] = null;
        }
    }

    public boolean contains(T x) {
        return isActive(index(x));
    }

    public void insert(T x){
        int index = myhash(x);
        if (isActive(index)) {
            return;
        }
        entries[index] = new HashEntry<>(x, true);
        if (size > entries.length / 2) {
            rehash();
        }
    }

    public void remove(T x) {
        int index = index(x);
        if (isActive(index)) {
            entries[index].isActive = false;
        }
    }


    /*********************************************************/


    private void allocate(int size) {
        entries = new HashEntry[size];
    }

    private boolean isActive(int index) {
        return entries[index] != null && entries[index].isActive;
    }

    /**
     *
     * @param x
     * @return
     */
    private int index(T x) {
        int index = myhash(x);
        int offset = 1;
        while (entries[index] != null && !x.equals(entries[index].element)) {
            index += offset;
            offset += 2;
            if (index >= entries.length) {
                index -= entries.length;
            }
        }
        // 感觉书上还是有缺陷，如果x不存在呢，返回myhash(x)真的好吗？
        return index;
    }

    private void rehash() {

    }

    /**
     * 除留余数法
     *
     * @param x
     * @return
     */
    private int myhash(T x) {
        return (x.hashCode() & 0x7fffffff) % size;
    }

    /**
     * Internal method to find a prime number at least as large as n.
     *
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for (; !isPrime(n); n += 2)
            ;

        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * Not an efficient algorithm.
     *
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }
}
