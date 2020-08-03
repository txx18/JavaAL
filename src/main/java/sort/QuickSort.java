package sort;

/**
 * @author Shane Tang
 * @create 2020-07-30 15:25
 */
public class QuickSort<T extends Comparable<? super T>> {

    public void sort(Comparable<T>[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    private int[] quickSort(Comparable<T>[] a, int l, int h) {
        if (l >= h) {
            return null;
        }
        int pivot = l + (int) (Math.random() * (h - l + 1));
        return null;
    }
}
