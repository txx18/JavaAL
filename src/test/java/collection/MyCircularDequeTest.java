package collection;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class MyCircularDequeTest {

//    MyCircularDeque<Integer> myCircularDeque = new MyCircularDeque<>(4);
    MyCircularDeque myCircularDeque = new MyCircularDeque(4);

    @Test
    public void insertFront() {
        for (int i = 0; i < 5; i++) {
            myCircularDeque.insertFront(i);
//            System.out.println(myCircularDeque);
        }
        System.out.println("insertFront myCircularDeque = " + myCircularDeque);
    }

    @Test
    public void insertLast() {
        for (int i = 0; i < 5; i++) {
            myCircularDeque.insertLast(i);
//            System.out.println(myCircularDeque);
        }
        System.out.println("insertLast myCircularDeque = " + myCircularDeque);
    }

    @Test
    public void deleteFront() {
        insertFront();
        for (int i = 0; i < 4; i++) {
            myCircularDeque.deleteFront();
            System.out.println(myCircularDeque);
        }
        System.out.println("deleteFront myCircularDeque = " + myCircularDeque);
    }

    @Test
    public void deleteLast() {
        insertFront();
//        insertLast();
        for (int i = 0; i < 3; i++) {
            myCircularDeque.deleteLast();
//            System.out.println(myCircularDeque);
        }
        System.out.println("deleteLast myCircularDeque = " + myCircularDeque);
    }

    @Test
    public void getFront() {
        insertFront();
        System.out.println(myCircularDeque.getFront());
    }

    @Test
    public void getRear() {
        insertFront();
        System.out.println(myCircularDeque.getRear());
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void isFull() {
    }


    @Test
    public void testDeque() {
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            deque.offer(i);
        }
        System.out.println("deque = " + deque);
        for (int i = 0; i < 10; i++) {
            System.out.println(deque.poll());
        }
        System.out.println("deque = " + deque);
    }
}