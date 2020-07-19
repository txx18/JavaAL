package sort;

/**
 * @author Shane Tang
 * @create 2020-07-19 23:26
 */
public class InsertionSort<T extends Comparable<? super T>> {

    public void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 记住待插入元素
            T x = arr[i];
            int hole = i;
            while (hole > 0 && x.compareTo(arr[hole - 1]) < 0) {
                arr[hole] = arr[hole - 1];
                hole--;
            }
            arr[hole] = x;
        }
    }
}
