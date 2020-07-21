package sort;

/**
 * TODO Fuck
 * @author Shane Tang
 * @create 2020-07-21 23:37
 */
public class HeapSort<T extends Comparable<? super T>> {

    public void sort(T[] a) {
        // 如果是升序排列，必须构造大根堆，因为heapSize只能从右边减1，右边是放大的
        // 堆化，构造大根堆
        int heapSize = a.length;
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            sink(a, i, a.length);
        }
        // 排序
        for (int i = a.length - 1; i > 0; i--) {
            // 最大的放到尾
            swap(a, 0, i);
            // 减小堆，继续堆化
            heapSize--;
                a[0] = a[heapSize - 1];

            sink(a, 0, heapSize);
        }
    }

    private void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private void sink(T[] elements, int hole, int heapSize) {
        // 记住待填入元素（放在0位置）
        T x = elements[hole];
        int left = hole * 2 + 1;
        int right = left + 1;
        while (true) {
            // 无左无右
            if (left > heapSize - 1) {
                break;
            }
            // 有左无右
            else if (right > heapSize - 1) {
                if (x.compareTo(elements[left]) > 0) {
                    break;
                } else {
                    elements[hole] = elements[left];
                    hole = left;
                    left = hole * 2 + 1;
                    right = left + 1;
                }
            }
            // 有左有右
            else {
                // 取左右孩子较小的
                int largerChildIndex = (elements[left].compareTo(elements[right]) > 0) ? left : right;
                // 和待填入的比较，如果待填入的小，直接可以填入，返回；如果待填入的不够小，空穴下移，循环继续
                if (x.compareTo(elements[largerChildIndex]) > 0) {
                    break;
                } else {
                    elements[hole] = elements[largerChildIndex];
                    hole = largerChildIndex;
                    left = hole * 2 + 1;
                    right = left + 1;
                }
            }
        }
        elements[hole] = x;
    }
}
