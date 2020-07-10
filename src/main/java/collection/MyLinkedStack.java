package collection;

import java.util.EmptyStackException;

/**
 * @author Shane Tang
 * @create 2020-07-10 22:44
 */
public class MyLinkedStack<T> implements MyStack<T>{

    private Node<T> head;
    private int size;
    /**
     * 防止多线程修改
     */
    private int modCount;

    public MyLinkedStack() {
    }

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

    @Override
    public int size() {
        return size;
    }

    // 前插，方便pop
    @Override
    public void push(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.next = head;
        head = newNode;
    }

    // 取第一个
    @Override
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Node<T> top = head;
        head = head.next;
        return top.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> cur = head; cur != null; cur = cur.next) {
            sb.append(cur.data).append(" ");
        }
        return sb.toString();
    }
}
