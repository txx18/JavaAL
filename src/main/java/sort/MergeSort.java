package sort;

/**
 * @author Shane Tang
 * @create 2020-07-26 19:16
 */
public class MergeSort<T extends Comparable<? super T>> {

    public void sort(T[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        mergeSort(a, 0, a.length - 1);
    }

    private void mergeSort(T[] a, int l, int h) {
        if (l >= h) {
            return;
        }
        int mid = l + ((h - l) / 2);
        mergeSort(a, l, mid);  // 含边界
        mergeSort(a, mid + 1, h); // 含边界
        merge(a, l, mid, h); // 含边界
    }

    private void merge(T[] aToMerge, int l, int mid, int h) {
        T[] help = ((T[]) new Comparable[h - l + 1]);
        int pl, pr, i;
        for (pl = l, pr = mid + 1, i = 0; pl <= mid && pr <= h;) { // 【典型错误】pl++, pr++, i++
            help[i++] = (aToMerge[pl].compareTo(aToMerge[pr]) <= 0) ? aToMerge[pl++] : aToMerge[pr++];
        }
        while (pl <= mid) {
            help[i++] = aToMerge[pl++];
        }
        while (pr <= h) {
            help[i++] = aToMerge[pr++];
        }
        // 把merge好的help装回a
        for (int j = 0; j < help.length; j++) {
            aToMerge[l + j] = help[j];
        }
    }
}
