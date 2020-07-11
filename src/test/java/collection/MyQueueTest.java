package collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyQueueTest {

    MyQueue<Integer> myQueue = new MyLinkedQueue<>();

    @Test
    public void enqueue() {
        for (int i = 0; i < 10; i++) {
            myQueue.enqueue(i);
        }
        System.out.println("enqueue myQueue = " + myQueue);
    }

    @Test
    public void deque() {
        enqueue();
        while (!myQueue.isEmpty()) {
            System.out.print(myQueue.deque() + " ");
        }
        System.out.println("deque myQueue = " + myQueue);
    }
}