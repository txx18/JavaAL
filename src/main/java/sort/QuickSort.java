package sort;

/**
 * @author Shane Tang
 * @create 2020-07-30 15:25
 */
public class QuickSort<T extends Comparable<? super T>> {

    /**
     * 元素个数
     */
    private static final int CUTOFF = 10;

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        quickSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void quickSort(T[] a, int l, int r) {
        // 如果小于一定个数的数组，采用插入排序
        if (r - l <= CUTOFF) {
            insertionSort(a, l, r);
            return;
        }
        // 选一个随机位置的数
        int pivot = l + (int) (Math.random() * (r - l + 1));
        // 放在r位置
        swapRef(a, pivot, r);
        // partition操作，返回等于区左右边界（边界是包含一个区边界元素的）
        int[] partitionIndexes = partition(a, l, r);
        // 递归
        quickSort(a, l, partitionIndexes[0] - 1);
        quickSort(a, partitionIndexes[1] + 1, r);
    }

    private static <T extends Comparable<? super T>> int[] partition(T[] a, int l, int r) {
        // 小于区右边界
        int ltRight = l - 1;
        // 大于区左边界
        int gtLeft = r;
        // 指针
        int p = l;
        while (p < gtLeft) {
            if (a[p].compareTo(a[r]) < 0) {
                swapRef(a, p, ++ltRight);
                p++;
            } else if (a[p].compareTo(a[r]) > 0) {
                // p不++因为换过来的arr[moreIndex-1]还没判断
                swapRef(a, p, --gtLeft);
            } else if (a[p].compareTo(a[r]) == 0) {
                p++;
            } else {

            }
        }
        // 交换 大于区域左边界 和 r位置，大于区域左边界变成了等于区域右边界
        swapRef(a, gtLeft, r);
        return new int[]{ltRight + 1, gtLeft};

    }

    // 如果泛型声明为<T>，不能解析compareTo方法
    private static <T extends Comparable<? super T>> void insertionSort(T[] arr, int l, int h) {
        int hole;
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

    private static <T extends Comparable<? super T>> void swapRef(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
