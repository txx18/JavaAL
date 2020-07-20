package sort;

/**
 * @author Shane Tang
 * @create 2020-07-20 23:40
 */
public class ShellSort<T extends Comparable<? super T>> {

    public void sort(T[] a) {
        int hole;
        for (int gap = a.length; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                T x = a[i];
                hole = i;
                while (hole >= gap && x.compareTo(a[hole - gap]) < 0) {
                    a[hole] = a[hole - gap];
                    hole -= gap;
                }
                a[hole] = x;
            }
        }
    }
}
