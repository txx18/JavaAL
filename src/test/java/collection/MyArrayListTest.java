package collection;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.util.ArrayList;
import java.util.Iterator;

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
        System.out.println("add myArrayList = " + myArrayList);
    }


    @Test
    public void remove() {
        add();
        System.out.println("myArrayList = " + myArrayList);
        myArrayList.remove(0);
        System.out.println("myArrayList = " + myArrayList);
        myArrayList.remove(myArrayList.size() - 1);
        System.out.println("myArrayList = " + myArrayList);
        myArrayList.remove(2);
        System.out.println("myArrayList = " + myArrayList);
    }

    @Test
    public void iterator() {
        add();
        Iterator<Integer> iterator = myArrayList.iterator();
        while (iterator.hasNext()) {
            // 可以做到只打印奇数
            if (iterator.next() % 2 == 0) {
                System.out.print(iterator.next() + " ");
            }

        }
    }

    @Test
    public void testArrayList() {
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        for (int i = 0; i < 5; i++) {
            arrayList.add(i);
        }
        System.out.println("arrayList = " + arrayList);
    }
}