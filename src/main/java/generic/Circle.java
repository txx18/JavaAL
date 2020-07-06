package generic;

public class Circle extends Shape {

    private double radius;

    public Circle(double rad) {
        radius = rad;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle: " + radius;
    }


}
