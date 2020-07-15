package hashtable;

/**
 * 可以放在散列表中的类的例子
 * @author Shane Tang
 * @create 2020-07-15 20:21
 */
public class Employee {

    private String name;

    private double salary;

    private int seniority;

    @Override
    public boolean equals(Object rhs) {
        return rhs instanceof Employee && name.equals(((Employee) rhs).name);
    }

    /**
     * 这里返回的是name的hashcode，也就是同名的员工hashcode相同
     * @return
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
