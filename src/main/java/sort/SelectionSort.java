package sort;

import static zhelper.ArrayUtils.*;

/**
 * 算法2.1 选择排序
 *
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-07 15:45
 */
public class SelectionSort implements Sort {


    @Override
    public void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                minIdx = less(a[j], a[minIdx]) ? j : minIdx;
            }
            swap(a, i, minIdx);
        }
    }
}
