package sort;

/**
 * @author Shane Tang
 * @create 2020-07-30 15:25
 */
public class QuickSort<T extends Comparable<? super T>> {

    private static final int CUTOFF = 10;

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> int[] quickSort(T[] a, int l, int h) {
        // 如果小于一定个数的数组，采用插入排序
        if (h - l <= CUTOFF) {
            insertionSort(a, l, h);
        }
        return null;
    }

    // 如果泛型声明为<T>，不能解析compareTo方法
    private static <T extends Comparable<? super T>> void insertionSort(T[] arr, int l, int h) {
        int hole;
        // TODO <=
        for (int i = l; i <= h; i++) {
            // 记住待插入元素
            T x = arr[i];
            // 空穴变量
            hole = i;
            while (hole > l && x.compareTo(arr[hole - 1]) < 0) {
                arr[hole] = arr[hole - 1];
                hole--;
            }
            // 移动空穴最后插入的思想在堆结构的swim和sink里使用过
            arr[hole] = x;
        }
    }
}
