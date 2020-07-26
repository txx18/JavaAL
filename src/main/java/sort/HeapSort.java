package sort;

/**
 *
 *
 * @author Shane Tang
 * @create 2020-07-21 23:37
 */
public class HeapSort<T extends Comparable<? super T>> {

    public void sort(T[] a) {
        if (a == null || a.length < 2) {
            return;
        }
        // 如果是升序排列，必须构造【大根堆】，因为heapSize只能从右边减1，右边放大的
        // 第一次堆化过程，构造大根堆
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            sink(a, i, a.length);
        }
        // 排序过程
/*        for (int i = a.length - 1; i > 0; i--) {
            // 最大的放到尾
            swap(a, 0, i);
            // 交换过后实质上减小堆了，继续堆化
            sink(a, 0, i);
        }*/
        int heapSize = a.length;
        swap(a, 0, heapSize - 1);
        heapSize--;
        while (heapSize > 0) {
            sink(a, 0, heapSize);
            swap(a, 0, heapSize - 1);
            heapSize--;
        }
    }

    private void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     *
     * @param elements
     * @param hole
     * @param heapSize 堆排序是利用本身数组，heapSize会变化
     */
    private void sink(T[] elements, int hole, int heapSize) {
        int child;
        T x = elements[hole];
        while (2 * hole + 1 < heapSize) {
            child = 2 * hole + 1;
            if (child != heapSize - 1 && elements[child].compareTo(elements[child + 1]) < 0) {
                child++;
            }
            if (x.compareTo(elements[child]) < 0) {
                elements[hole] = elements[child];
            }
            else {
                break;
            }
            hole = child;
        }
        elements[hole] = x;
        // （我）
/*        // 记住待填入元素（放在0位置）
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
        elements[hole] = x;*/

    }
}
