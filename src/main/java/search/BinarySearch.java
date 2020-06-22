package search;

/**
 * 二分查找
 *
 * @author Shane Tang
 * @create 2020-06-10 17:10
 */
public class BinarySearch<Key extends Comparable<Key>> {

    public static void main(String[] args) {
        Integer[] arr = {3, 5, 11, 17, 21, 23, 28, 30, 32, 50, 64, 78, 81, 95, 101};
        Integer[] arr2 = {-1,0,3,5,9,12};
        BinarySearch<Integer> obj = new BinarySearch<>();
        int resLoop = obj.rankLoop(arr, 105);
        int resRecur = obj.rankLoop(arr, 105);
        System.out.println("resLoop = " + resLoop);
        System.out.println("resRecur = " + resRecur);
    }

    public int rankRecur(Key[] keys, Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        int mid = lo + ((hi - lo) >> 1);
        int cmp = key.compareTo(keys[mid]);
        // 比中间小
        if (cmp < 0) {
            return rankRecur(keys, key, lo, mid - 1);
        }
        // 比中间大
        else if (cmp > 0) {
            return rankRecur(keys, key, mid + 1, hi);
        }
        else {
            return mid;
        }
    }

    /**
     *
     * @param keys
     * @param key
     * @return 命中：index 未命中：
     */
    public int rankLoop(Key[] keys, Key key) {
        int lo = 0;
        int hi = keys.length - 1;
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
}
