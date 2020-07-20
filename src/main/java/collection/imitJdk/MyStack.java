package collection.imitJdk;

import java.util.EmptyStackException;

/**
 * @author Shane Tang
 * @create 2020-01-30 12:02
 */
public class MyStack<E> extends MyVector<E> {

    public MyStack() {

    }

    public E push(E e) {
        // 继承自Vector
        add(e);
        return e;
    }

    public synchronized E pop() {
        E e = peek();
        int size = size();
        remove(size - 1);
        return e;
    }

    public synchronized E peek() {
        int len = size();
        if (len == 0) {
            throw new EmptyStackException();
        }
        return get(len - 1);
    }

    @Override
    public String toString(){
        return MyArrays.toString(this.elementData);
    }
}
