package sort;

import static zhelper.ArrayUtils.less;
import static zhelper.ArrayUtils.swap;

public class HeapSort implements Sort {
    @Override
    public void sort(Comparable[] a) {
        int heapSize = a.length;
        // 构造堆，从最后一个叶子的父节点开始，倒着遍历，sink
        for (int k = heapSize / 2; k >= 0 ; k--) {
            sink(a, k, heapSize - 1);
        }
        // 将最大元素即a[0]和a[N-1]交换，并重新sink
        while (heapSize > 0) {
            swap(a, 0, --heapSize);
            sink(a, 0, heapSize - 1);
        }
    }

    /**
     *
     * @param pq
     * @param index
     * @param heapSize
     */
    public void sink(Comparable[] pq, int index, int heapSize) {
        while (2 * index <= heapSize) {
            // 选出左右孩子中较大的那个
            // 左孩子
            int j = 2 * index;
            // 如果左孩子 < 右孩子 就选中右孩子
            if (j < heapSize && less(pq[j], pq[j + 1])) {
                j++;
            }
            // 如果父比子都大，结束循环
            if (!less(pq[index], pq[j])) {
                break;
            }
            // 反之父比子小，循环不结束，父滚下去
            swap(pq, index, j);
            // 可能在子节点处继续打破堆的有序状态，所以需要继续判断
            index = j;
        }
    }
}
