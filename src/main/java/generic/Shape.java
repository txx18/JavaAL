package generic;

/**
 * @author Shane Tang
 * @create 2020-07-06 17:05
 */
public abstract class Shape implements Comparable<Shape> {


    public abstract double area();

    public abstract double perimeter();

    @Override
    public int compareTo(Shape rhs) {
        double diff = area() - rhs.area();
        if (diff == 0)
            return 0;
        else if (diff < 0)
            return -1;
        else
            return 1;
    }

    public double semiperimeter() {
        return perimeter() / 2;
    }
}
