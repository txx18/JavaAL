package ztest;

import sort.HeapSort;
import sort.MergeSort;

import java.util.Arrays;

import static ztest.ArrayUtilsGeneric.*;

/**
 * 对数器
 *
 * @author Shane Tang
 * @create 2019-09-16 20:31
 */
public class ArrayTestHelperGeneric {

    private void solution(Integer[] arr) {
        MergeSort<Integer> obj = new MergeSort<>();
        obj.sort(arr);
    }

    // for test
    public void comparator(Integer[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static void main(String[] args) {

        ArrayTestHelperGeneric test = new ArrayTestHelperGeneric();
        long startTime = System.currentTimeMillis();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            Integer[] arr1 = generateRandomArray(maxSize, maxValue);
            Integer[] arr2 = copyArray(arr1);
//            System.out.print("generate  ：");
//            printArray(arr1);
//            System.out.println("---------------------------------------------");
            try {
                test.solution(arr1);
            }catch (Exception e) {
                e.printStackTrace();
                System.out.print("生成：");
                printArray(arr2);
                System.out.println("---------------------------------------------");
                return;
            }
            test.comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                System.out.print("生成：");
                printArray(arr1);
                System.out.println("---------------------------------------------");
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
            Integer[] arr = generateRandomArray(maxSize, maxValue);
            System.out.println("一次成功实验展示:");
            System.out.print("生成：");
            printArray(arr);
            System.out.println("---------------------------------------------");
            test.solution(arr);
            System.out.print("测试&对照：");
            printArray(arr);
        }
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println("程序运行时间：" + runningTime + "ms");
    }
}
