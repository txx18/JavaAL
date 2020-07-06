package generic;

/**
 * @author Shane Tang
 * @create 2020-07-06 15:52
 */
public class FindMaxDemo {

    public static Comparable findMax(Comparable[] arr) {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(arr[maxIndex]) > 0) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }



}
