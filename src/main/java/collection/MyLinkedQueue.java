package collection;

import java.util.EmptyStackException;

/**
 * @author Shane Tang
 * @create 2020-07-11 9:13
 */
public class MyLinkedQueue<T> implements MyQueue<T> {

    private int size;

    private Node<T> beginMarker;

    private Node<T> endMarker;

    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> pre;

        public Node() {

        }

        public Node(T x) {
            this.data = x;
        }
    }

    public MyLinkedQueue() {
        beginMarker = new Node<>();
        endMarker = new Node<>();
        beginMarker.next = endMarker;
        endMarker.pre = beginMarker;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 从尾入队
     * @param x
     */
    @Override
    public void enqueue(T x) {
        Node<T> tNode = new Node<>(x);
        Node<T> oldTail = endMarker.pre;
        oldTail.next = tNode;
        tNode.pre = oldTail;
        tNode.next = endMarker;
        endMarker.pre = tNode;
        size++;
    }

    // 从头出队方便
    @Override
    public T deque() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Node<T> oldHead = beginMarker.next;
        beginMarker.next = oldHead.next;
        size--;
        oldHead.next = null; // 想释放掉老指针
        return oldHead.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> cur = beginMarker.next; cur != endMarker; cur = cur.next) {
            sb.append(cur.data).append(" ");
        }
        return sb.toString();
    }
}
