package generic;

/**
 * 泛型类
 *
 * @author Shane Tang
 * @create 2020-07-06 15:26
 */
public class MemoryCell {

    private Object storedVal;

    public Object read(){
        return storedVal;
    }

    public void write(Object x) {
        storedVal = x;
    }
}
