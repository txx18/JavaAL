package collection;

import java.util.Iterator;

/**
 * @author Shane Tang
 * @create 2020-07-08 22:18
 */
public class MyLinkedList<T> implements Iterable<T> {

    private Node<T> beginMarker;
    private Node<T> endMarker;
    private int size;
    private int modCount;

    public MyLinkedList() {
        this.beginMarker = new Node<>();
        this.endMarker = new Node<>();
        beginMarker.next = endMarker;
        endMarker.pre = beginMarker;
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;
        private Node<T> pre;

        public Node() {

        }

        public Node(T x) {
            this.item = x;
        }
    }

    /**
     * 默认后插
     * @param x
     * @return
     */
    public boolean add(T x) {
        Node<T> toAdd = new Node<T>(x);
        Node<T> oldTail = endMarker.pre;
        oldTail.next = toAdd;
        toAdd.pre = oldTail;
        toAdd.next = endMarker;
        endMarker.pre = toAdd;
        size++;
        modCount++;
        return true;
    }

    public void add(int index, T x) {

    }

    public T get(int index) {
        return null;
    }

    public T set(int index, T x) {
        return null;
    }

    public T remove(int index) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> cur = beginMarker.next; cur != endMarker; cur = cur.next) {
            sb.append(cur.item).append(" ");
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
