package collection;

import java.util.Iterator;

/**
 * 算法1.3 FIFO队列
 *
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-06 14:41
 */
public class QueueFIFO<E> implements Iterable<E> {

    public static void main(String[] args) {
        QueueFIFO<String> queue = new QueueFIFO<>();
        System.out.println(queue.isEmpty());
        System.out.println(queue.size());
        queue.offer("n");
        queue.offer("u");
        queue.offer("d");
        queue.offer("t");
        // 测试foreach
        for (String e : queue) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
    }

    /**
     * 队首，最先进的
     */
    private Node<E> front;
    /**
     * 队尾，最后进的
     */
    private Node<E> rear;

    private int size;

    private int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向表尾添加元素
     * @param e
     */
    public void offer(E e) {
        Node oldRear = rear;
        rear = new Node<E>(e);
        rear.next = null;
        if (size == 0) {
            front = rear;
        }else {
            oldRear.next = rear;
        }
        size++;
    }

    /**
     * 从表头删除元素
     * @return
     */
    public E poll() {
        Node<E> oldFront = front;
        front = front.next;
        if (size == 0) {
            rear = null;
        }
        size--;
        return oldFront.e;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<E> {
        /**
         * 当前指针
         */
        private Node<E> cur;

        public ListIterator() {
            cur = front;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            E e = cur.e;
            cur = cur.next;
            return e;
        }
    }

    private class Node<E> {

        private E e;

        private Node next;

        public Node(E e) {
            this.e = e;
        }
    }
}
