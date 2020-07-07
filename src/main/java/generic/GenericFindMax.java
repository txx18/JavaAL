package generic;

import java.util.Comparator;

/**
 * @author Shane Tang
 * @create 2020-07-06 16:37
 */
public class GenericFindMax {

    // 泛型方法
    public static <T> boolean contains(T[] arr, T x) {
        for (T val : arr) {
            if (x.equals(val)) {
                return true;
            }
        }
        return false;
    }

    // T不使用类型限界，编译器不能证明compareTo是合法的
/*    public static <T> T findMax(T[] arr) { //
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) { // 编译错误
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }*/

    // 第一种写法，能够被编译，但是Comparable如今是泛型的
    public static <T extends Comparable> T findMax1(T[] arr) {
            int maxIndex = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].compareTo(arr[maxIndex]) > 0) { // 无编译错误
                    maxIndex = i;
                }
            }
            return arr[maxIndex];
    }

    // 第二种写法
    // 只知道Circle IS A Comparable<Shape> 但是不知道 IS A Comparable<Circle>
    public static <T extends Comparable<T>> T findMax2(T[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) { // 无编译错误
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    // 第三种写法
    // 应该说T IS A Comparable<?>, ?是 T的父类
    public static <T extends Comparable<? super T>> T findMax3(T[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) { // 无编译错误
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }


}
