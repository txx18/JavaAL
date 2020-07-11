package collection;

/**
 * @author Shane Tang
 * @create 2020-07-11 9:10
 */
public interface MyQueue<T> {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    void enqueue(T x);

    T deque();
}
