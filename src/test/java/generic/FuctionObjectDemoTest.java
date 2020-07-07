package generic;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class FuctionObjectDemoTest {

    @Test
    public void functionObjectFindMax() {

        Shape[] shapeArr = {new Circle(2.5), new Rectangle(6, 2)};
        String[] strArr = {"Joe", "Bob", "Bill", "Zeke"};
//        System.out.println(GenericFindMax.findMax1(strArr));
//
//        System.out.println(GenericFindMax.findMax2(strArr));
//        System.out.println(GenericFindMax.findMax2(shapeArr));

        System.out.println(FuctionObjectDemo.findMax(strArr, new StringComparator()));
        System.out.println(FuctionObjectDemo.findMax(shapeArr, new ShapeComparator()));
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
    }

    private static class ShapeComparator implements Comparator<Shape>{
        @Override
        public int compare(Shape o1, Shape o2) {
            return o1.compareTo(o2);
        }


    }
}