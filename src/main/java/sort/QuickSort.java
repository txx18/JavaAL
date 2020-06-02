package sort;

import static zhelper.ArrayUtils.*;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-18 22:08
 */
public class QuickSort implements Sort {


    @Override
    public void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        recur(a, 0, a.length - 1);
    }

    private void recur(Comparable[] a, int l, int r) {
        if(l >= r) {
            return;
        }
        int pivotIndex = l + (int) (Math.random() * (r - l + 1));
        // pivot放到r去，因为我的partition是直接以[r]做切分的
        swap(a, pivotIndex, r);
        int[] partitionIndexes =  partition(a, l, r);
        recur(a, l,partitionIndexes[0] - 1);
        recur(a, partitionIndexes[1] + 1, r);
    }

    /**
     * 荷兰国旗问题
     * @param a
     * @param l
     * @param r
     * @return 等于区左右边界
     */
    public int[] partition(Comparable[] a, int l, int r) {
        // 等于区左边界
        int elt = l;
        // 等于区右边界
        int egt = r;
        // 指针
        int i = l;
        Comparable pivot = a[r];
        while (i <= egt) { // <=
            int cmp = a[i].compareTo(pivot);
            if (cmp < 0) {
                swap(a, i++, elt++);
            }else if (cmp > 0) {
                // i不要++，因为交换过来的[egt]还没被判断
                swap(a, i , egt--);
            }else { // [i] == pivot
                i++;
            }
        }
        return new int[] {elt, egt};
    }

}
