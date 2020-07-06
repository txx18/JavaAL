package generic;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenericFindMaxTest {

    @Test
    public void contains() {
    }

    @Test
    public void findMax() {
        Shape[] shapeArr ={new Circle(2.5), new Rectangle(6, 2)};
        String[] strArr = {"Joe", "Bob", "Bill", "Zeke"};
//        System.out.println(GenericFindMax.findMax1(strArr));
//
//        System.out.println(GenericFindMax.findMax2(strArr));
//        System.out.println(GenericFindMax.findMax2(shapeArr));

        System.out.println(GenericFindMax.findMax3(strArr));
        System.out.println(GenericFindMax.findMax3(shapeArr));
    }
}