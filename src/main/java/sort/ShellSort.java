package sort;

import static zhelper.ArrayUtils.*;

/**
 *
 * 算法2.3 希尔排序
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-08 11:39
 */
public class ShellSort implements Sort {


    @Override
    public void sort(Comparable[] a) {
        int h = 1;
        // 递增序列 从N/3开始递减至1
        while (h < a.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                    swap(a, j, j - h);
                }
            }
            h /= 3;
        }
    }
}
