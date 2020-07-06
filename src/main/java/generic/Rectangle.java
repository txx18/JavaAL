package generic;

public class Rectangle extends Shape {
    public Rectangle(double len, double wid) {
        length = len;
        width = wid;
    }

    @Override
    public double area() {
        return length * width;
    }

    @Override
    public double perimeter() {
        return 2 * (length + width);
    }

    @Override
    public String toString() {
        return "Rectangle: " + length + " " + width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public void stretch(double factor) {
        if (factor <= 0)
            throw new IllegalArgumentException();

        if (length > width)
            length *= factor;
        else
            width *= factor;
    }

    private double length;
    private double width;
}