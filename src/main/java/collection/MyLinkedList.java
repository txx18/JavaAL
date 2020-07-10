package collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Shane Tang
 * @create 2020-07-08 22:18
 */
public class MyLinkedList<T> implements MyList<T>, Iterable<T> {

    private Node<T> beginMarker;
    private Node<T> endMarker;
    private int size;
    /**
     * 防止多线程修改
     */
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

    @Override
    public int size() {
        return size;
    }

    // 自然转换为在index插入的特例
    @Override
    public void add(T x) {
        add(size(), x);
    }

    // index转换为具体的Node
    public void add(int index, T x) {
        addBefore(getNode(index, 0, size()), x);
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T set(int index, T x) {
        Node<T> p = getNode(index);
        T old = p.data;
        p.data = x;
        return old;
    }

    @Override
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

        private Node<T> cursor = beginMarker.next;
        private int expectModCount = modCount;
        private boolean canMod = false;

        @Override
        public boolean hasNext() {
            return cursor != endMarker;
        }

        @Override
        public T next() {
            if (expectModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();/*NoSuchElementException*/
            }
            Node<T> cur = cursor;
            cursor = cursor.next;
            canMod = true; /*调用next则可以remove*/
            return cur.data;
        }

        @Override
        public void remove() {
            if (expectModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!canMod) {
                throw new IllegalStateException();
            }
            MyLinkedList.this.remove(cursor.pre);
            expectModCount++;
            canMod = false;
        }
    }
}
