package collection;

import java.util.Iterator;

/**
 * 算法1.2 下压栈，链表实现
 *
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-06 14:11
 */
public class LinkedStack<E> implements Iterable<E> {

    public static void main(String[] args) {
        LinkedStack<String> linkedStack = new LinkedStack<>();
        System.out.println(linkedStack.isEmpty());
        System.out.println(linkedStack.size());
        linkedStack.push("n");
        linkedStack.push("u");
        linkedStack.push("d");
        linkedStack.push("t");
        // 测试foreach
        for (String e : linkedStack) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.isEmpty());
    }

    /**
     * 栈顶，头结点
     */
    private Node<E> top;
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
     * @param e
     */
    public void push(E e) {
        Node oldTop = top;
        top = new Node<>(e);
        top.next = oldTop;
        size++;
    }

    public E pop(){
        Node<E> pop = top;
        top = top.next;
        size--;
        return pop.e;
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
            cur = top;
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
