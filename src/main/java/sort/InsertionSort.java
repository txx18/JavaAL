package sort;

/**
 * @author Shane Tang
 * @create 2020-07-19 23:26
 */
public class InsertionSort<T extends Comparable<? super T>> {

    public void sort(T[] arr) {
        int hole;
        for (int i = 1; i < arr.length; i++) {
            // 记住待插入元素
            T x = arr[i];
            // 空穴变量
            hole = i;
            while (hole > 0 && x.compareTo(arr[hole - 1]) < 0) {
                arr[hole] = arr[hole - 1];
                hole--;
            }
            // 移动空穴最后插入的思想在堆结构的swim和sink里使用过
            arr[hole] = x;
        }
    }
}
