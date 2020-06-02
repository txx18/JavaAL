package collection;

import java.util.Iterator;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-05 16:56
 */
public class ResizingArrayStack<E> implements Iterable<E> {

    public static void main(String[] args) {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>(10);
        System.out.println(stack.isEmpty());
        System.out.println(stack.size());
        stack.push(0);
        stack.push(1);
        stack.push(2);
        // 测试foreach
        for (Integer e : stack) {
            System.out.print(e + " ");
        }
        System.out.println();
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
    }

    private E[] elements;
    /**
     * 元素个数
      */
    private int size;

    public ResizingArrayStack(int cap) {
        this.elements = ((E[]) new Object[cap]); // new泛型数组
        this.size = 0;
    }

    private int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public void push(E e) {
        if(size == elements.length) {
            resize(2 * elements.length);
        }
        elements[size++] = e;
    }

    private void resize(int newLength) {
        E[] tmp = (E[]) new Object[newLength];
        for (int i = 0; i < size; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
    }

    public E pop() {
        E pop = elements[--size];
        elements[size] = null;
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        return pop;
    }

    /**
     *
     * @return Iterator接口的实现类
     */
    @Override
    public Iterator<E> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * 后进先出Iterator的实现类
     */
    private class ReverseArrayIterator implements Iterator<E> {
        /**
         * 元素个数
         */
        private int i = size;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public E next() {
            return elements[--i];
        }

    }
}
