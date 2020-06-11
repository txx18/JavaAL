package st;

/**
 * 二分查找
 *
 * @author Shane Tang
 * @create 2020-06-10 17:10
 */
public class BinarySearch<Key extends Comparable<Key>> {

    public int rank(Key[] keys, Key key, int lo, int hi) {
        if (hi < lo) {
            return lo;
        }
        int mid = lo + ((hi - lo) >> 1);
        int cmp = key.compareTo(keys[mid]);
        // 比中间小
        if (cmp < 0) {
            return rank(keys, key, lo, mid - 1);
        }
        // 比中间大
        else if (cmp > 0) {
            return rank(keys, key, mid + 1, hi);
        }
        else {
            return mid;
        }
    }
}
