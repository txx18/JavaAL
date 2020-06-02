import java.util.Arrays;

import sort.*;

import static util.ArrayUtils.*;

/**
 * 对数器
 *
 * @author Shane Tang
 * @create 2019-09-16 20:31
 */
public class ArrayTestHelper {

    private static void solution(Comparable[] arr) {
        Sort obj = new HeapSort<Integer>();
        obj.sort(arr);
    }

    // for test
    public static void comparator(Comparable[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            Comparable[] arr1 = generateRandomArray(maxSize, maxValue);
            Comparable[] arr2 = copyArray(arr1);
//            System.out.print("generate  ：");
//            printArray(arr1);
//            System.out.println("---------------------------------------------");
            solution(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.print("测试：");
                printArray(arr1);
                System.out.println("---------------------------------------------");
                System.out.print("对照：");
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
        if (succeed) {
            // 一次实验展示
            Comparable[] arr = generateRandomArray(maxSize, maxValue);
            System.out.println("一次成功实验展示:");
            System.out.print("生成：");
            printArray(arr);
            System.out.println("---------------------------------------------");
            solution(arr);
            System.out.print("测试：");
            printArray(arr);
        }
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println("程序运行时间：" + runningTime + "ms");
    }
}
