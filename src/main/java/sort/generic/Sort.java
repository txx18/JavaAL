package sort.generic;

import java.util.Comparator;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-07 15:41
 */
public abstract class Sort<T extends Comparable<T>> {

    public abstract void sort(T[] a);

    protected boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    protected void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
