package sort;


import static zhelper.ArrayUtils.*;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-07 16:27
 */
public class InsertionSort implements Sort {


    @Override
    public void sort(Comparable[] a) {
        // 从第一张开始摸牌
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]) ; j--) {
                swap(a, j, j - 1);
            }
        }
    }
}
