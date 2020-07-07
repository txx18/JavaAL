package generic;

import java.util.Comparator;

/**
 * 函数对象
 * @author Shane Tang
 * @create 2020-07-07 15:24
 */
public class FuctionObjectDemo {

    public static <T> T findMax(T[] arr, Comparator<? super T> comparator) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (comparator.compare(arr[i], arr[maxIndex]) > 0) { // 无编译错误
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

}
