package hashtable;

/**
 * 平方探测法
 *
 * @author Shane Tang
 * @create 2020-07-15 22:44
 */
public class QuadraticProbingHashTable<T> {

    // Simple main
    public static void main(String[] args) {
        QuadraticProbingHashTable<String> H = new QuadraticProbingHashTable<>();


        long startTime = System.currentTimeMillis();

        final int NUMS = 2000000;
        final int GAP = 37;

        System.out.println("Checking... (no more output means success)");


        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            H.insert("" + i);
        for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
            if (H.insert("" + i))
                System.out.println("OOPS!!! " + i);
        for (int i = 1; i < NUMS; i += 2)
            H.remove("" + i);

        for (int i = 2; i < NUMS; i += 2)
            if (!H.contains("" + i))
                System.out.println("Find fails " + i);

        for (int i = 1; i < NUMS; i += 2) {
            if (H.contains("" + i))
                System.out.println("OOPS!!! " + i);
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Elapsed time: " + (endTime - startTime));
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<T>[] entries;

    /**
     * 懒惰删除依然算占用
     */
    private int occupied;

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

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE); // this(成员变量) 必须是static的
    }

    public QuadraticProbingHashTable(int size) {
        // 这一步只是新建了引用对象数组，并没有把数组初始化
        entries = new HashEntry[size]; // allocate
        // 初始化Empty
        makeEmpty();
    }

    public void makeEmpty() {
        for (int i = 0; i < entries.length; i++) {
            entries[i] = null;
        }
        occupied = 0;
//        size = 0;

    }

    public boolean contains(T x) {
        return isActive(index(x));
    }

    public boolean insert(T x) {
        int index = index(x);
        if (isActive(index)) {
            return false;
        }
        if (entries[index] == null) {
            ++occupied;
        }
        // 上下顺序不能颠倒
        entries[index] = new HashEntry<>(x, true);
        size++;
        if (occupied > entries.length / 2) { // 不是size>
            rehash();
        }
        return true;
    }

    public boolean remove(T x) {
        int index = index(x);
        if (isActive(index)) {
            // 懒惰删除
            entries[index].isActive = false;
            size--;
            return true;
        }
        return false;
    }


    /*********************************************************/


    private void allocate(int size) {
        entries = new HashEntry[nextPrime(size)];
    }

    private boolean isActive(int index) {
        return entries[index] != null && entries[index].isActive;
    }

    /**
     * @param x
     * @return
     */
    private int index(T x) {
        int index = myhash(x);
        int offset = 1;
        try {
            while (entries[index] != null && !x.equals(entries[index].element)) {
                index += offset;
                offset += 2;
                if (index >= entries.length) {
                    index -= entries.length;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // （书）感觉书上还是有缺陷，如果x不存在呢，返回myhash(x)真的好吗？
        return index;
    }

    /**
     * 再散列
     */
    private void rehash() {
        HashEntry<T>[] oldEntries = entries;
        // 建立新散列表
//        allocate(nextPrime(oldEntries.length * 2)); // 不是size*2
        allocate(oldEntries.length * 2); // 不是size*2
        occupied = 0;
        size = 0;
        // 遍历原散列表
        for (int i = 0; i < oldEntries.length; i++) {
//            if (isActive(i)) { // 不能用isActive（isActive看的是entries而不是oldEntries）
            if (oldEntries[i] != null && oldEntries[i].isActive) {
                insert(oldEntries[i].element);
            }
        }
    }

    /**
     * 除留余数法
     *
     * @param x
     * @return
     */
    private int myhash(T x) {
        return (x.hashCode() & 0x7fffffff) % entries.length;
/*        int hashVal = x.hashCode();
        hashVal %= entries.length;
        if (hashVal < 0)
            hashVal += entries.length;
        return hashVal;*/
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
