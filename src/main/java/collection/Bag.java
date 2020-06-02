package collection;

import java.util.Iterator;

/**
 * 算法1.4 背包
 *
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-06 15:08
 */
public class Bag<E> implements Iterable<E> {

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        System.out.println(bag.isEmpty());
        System.out.println(bag.size());
        bag.add("n");
        bag.add("u");
        bag.add("d");
        bag.add("t");
        // 测试foreach
        for (String e : bag) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println(bag.isEmpty());
        System.out.println(bag.size());
    }

    /**
     * 栈顶，头结点
     */
    private Node<E> first;
    /**
     * 元素个数
     */
    private int size;

    private int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向表头添加元素
     *
     * @param e
     */
    public void add(E e) {
        Node oldTop = first;
        first = new Node<>(e);
        first.next = oldTop;
        size++;
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
            cur = first;
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
