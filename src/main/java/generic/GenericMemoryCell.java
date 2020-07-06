package generic;

/**
 * @author Shane Tang
 * @create 2020-07-06 16:07
 */
public class GenericMemoryCell<T> {

    private T storedVal;

    public T read() {
        return storedVal;
    }

    public void write(T x) {
        storedVal = x;
    }
}
