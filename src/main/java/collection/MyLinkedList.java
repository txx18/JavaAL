package collection;

import com.sun.org.apache.xpath.internal.objects.XNull;

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
        this.beginMarker = new Node<T>();
        this.endMarker = new Node<T>();
        beginMarker.next = endMarker;
        endMarker.pre = beginMarker;
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

    public int size() {
        return size;
    }

    // 自然转换为在index插入的特例
    public void add(T x) {
        add(size(), x);
    }

    // index转换为具体的Node
    public void add(int index, T x) {
        addBefore(getNode(index, 0, size()), x);
    }

    public T get(int index) {
        return getNode(index).data;
    }

    public T set(int index, T x) {
        Node<T> p = getNode(index);
        T old = p.data;
        p.data = x;
        return old;
    }

    public T remove(int index) {
        return remove(getNode(index));
    }

    /*************隐藏区*************************/

    private Node<T> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    // 核心方法，get到要操作的Node
    private Node<T> getNode(int index, int lower, int upper) {
        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }
        if (index < (size() >> 1)) {
            Node<T> cur = beginMarker.next;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            return cur;
        } else {
            Node<T> cur = endMarker;
            for (int i = size(); i > index; i--) {
                cur = cur.pre;
            }
            return cur;
        }
    }

    private void addBefore(Node<T> p, T x) {
        Node<T> toAdd = new Node<T>(x);
        Node<T> oldPre = p.pre;
        oldPre.next = toAdd;
        toAdd.pre = oldPre;
        toAdd.next = p;
        p.pre = toAdd;
        size++;
        modCount++;
    }

    private T remove(Node<T> p) {
        Node<T> pre = p.pre;
        pre.next = p.next;
        p.next.pre = pre;
        size--;
        modCount++;
        return p.data;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> cur = beginMarker.next; cur != endMarker; cur = cur.next) {
            sb.append(cur.data).append(" ");
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }

        @Override
        public void remove() {

        }
    }
}
