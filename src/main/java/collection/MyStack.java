package collection;

/**
 * @author Shane Tang
 * @create 2020-07-10 22:53
 */
public interface MyStack<T> {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void push(T x);

    T pop();
}
