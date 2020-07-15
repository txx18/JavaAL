package hashtable;

import java.util.LinkedList;
import java.util.List;

/**
 * 分离链接法
 *
 * @author Shane Tang
 * @create 2020-07-15 19:53
 */
public class SeparateChainingHashTable<T> {

    // Simple main
    public static void main( String [ ] args )
    {
        SeparateChainingHashTable<Integer> H = new SeparateChainingHashTable<>( );

        long startTime = System.currentTimeMillis( );

        final int NUMS = 2000000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );

        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            H.insert( i );
        for( int i = 1; i < NUMS; i+= 2 )
            H.remove( i );

        for( int i = 2; i < NUMS; i+=2 )
            if( !H.contains( i ) )
                System.out.println( "Find fails " + i );

        for( int i = 1; i < NUMS; i+=2 )
        {
            if( H.contains( i ) )
                System.out.println( "OOPS!!! " +  i  );
        }

        long endTime = System.currentTimeMillis( );

        System.out.println( "Elapsed time: " + (endTime - startTime) );
    }

    private static final int DEFAULT_TABLE_SIZE = 10;

    private LinkedList<T>[] lists;

    /**
     * size默认都指的是整个数据结构包含的元素个数
     */
    private int size;

    private void rehash() {

    }

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {
        // 初始化一个大小为>=size的第一个素数的散列表
        lists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new LinkedList<>();
        }
    }

    public void makeEmpty() {
        for (int i = 0; i < lists.length; i++) {
            lists[i].clear();
        }
        size = 0;
    }

    public void insert(T x) {
        if (contains(x)) {
            return;
        }
        // 书上这里用add，我觉得没有前插啊
        lists[myhash(x)].addFirst(x);
        if (++size > lists.length) {
            rehash();
        }
    }

    public void remove(T x) {
        lists[myhash(x)].remove(x);
        size--;
    }

    public boolean contains(T x) {
        return lists[myhash(x)].contains(x);
    }

    /**
     * 除留余数法
     *
     * @param x
     * @return
     */
    private int myhash(T x) {
        return (x.hashCode() & 0x7fffffff) % lists.length;
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
