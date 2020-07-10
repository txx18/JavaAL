package collection;

/**
 * @author Shane Tang
 * @create 2020-07-10 22:47
 */
public interface MyList<T> {

    int size();

    T get(int index);

    T set(int index, T x);

    void add(T x);

    T remove(int index);

}
