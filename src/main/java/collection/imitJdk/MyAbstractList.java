package collection.imitJdk;

/**
 * @author Shane Tang
 * @create 2020-01-09 10:24
 */
public abstract class MyAbstractList<E> {

    /**
     * 记录结构发生变化的次数
     */
    protected transient int modCount = 0;
}
