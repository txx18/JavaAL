package sort;

import static zhelper.ArrayUtils.*;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-12 10:00
 */
public class MergeSort implements Sort {

    private Comparable[] help;


    @Override
    public void sort(Comparable[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        this.help = new Comparable[a.length];
        topDownRecur(a, 0, a.length - 1);
//        bottomUp(a);
    }


    /**
     * 自顶向下归并排序
     * 在l~r排序
     *
     * @param a
     * @param l
     * @param r
     */
    private void topDownRecur(Comparable[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        topDownRecur(a, l, mid);
        topDownRecur(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    /**
     * 原地归并的抽象方法
     *
     * @param a
     * @param l
     * @param mid
     * @param r
     */
    private void merge(Comparable[] a, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        // 将a 复制到 help 
        for (int i = l; i <= r; i++) {
            help[i] = a[i];
        }
        // merge 回 a
        for (int i = l; i <= r; i++) {
            if (p1 > mid) {
                a[i] = help[p2++];
            } else if (p2 > r) {
                a[i] = help[p1++];
            } else if (less(help[p1], help[p2])) {
                a[i] = help[p1++];
            } else { // >=
                a[i] = help[p2++];
            }
        }

    }

    /**
     * 自底向上归并排序
     * @param a
     */
    private void bottomUp(Comparable[] a) {
        for (int size = 1; size < a.length; size += size) {
            for (int l = 0; l < a.length - size; l += size + size) {
                merge(a, l, l + size - 1, Math.min(l + size + size - 1, a.length - 1));
            }
        }
    }
}
