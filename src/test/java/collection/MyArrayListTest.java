package collection;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {

    MyArrayList<Integer> myArrayList = new MyArrayList<>();


    @Test
    public void get() {
    }

    @Test
    public void set() {
    }

    @Test
    public void add() {
        for (int i = 0; i < 10; i++) {
            myArrayList.add(i);
        }
        System.out.println("myArrayList = " + myArrayList);
    }


    @Test
    public void remove() {
        for (int i = 0; i < 10; i++) {
            myArrayList.add(i);
        }
        System.out.println("myArrayList = " + myArrayList);
        myArrayList.removeItem(0);
        System.out.println("myArrayList = " + myArrayList);
        myArrayList.removeItem(myArrayList.size() - 1);
        System.out.println("myArrayList = " + myArrayList);
    }

    @Test
    public void iterator() {
    }
}