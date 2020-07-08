package collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyLinkedListTest {

    @Test
    public void addLast() {
        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            myLinkedList.add(i);
        }
        System.out.println("myLinkedList = " + myLinkedList);
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void get() {
    }

    @Test
    public void set() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void iterator() {
    }
}