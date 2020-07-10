package collection;

import org.junit.Test;

public class MyStackTest {

//    MyStack<Integer> myStack = new MyArrayStack<Integer>();
    MyStack<Integer> myStack = new MyLinkedStack<Integer>();

    @Test
    public void push() {
        for (int i = 0; i < 10; i++) {
            myStack.push(i);
        }
        System.out.println("push myStack = " + myStack);
    }

    @Test
    public void pop() {
        push();
        while (!myStack.isEmpty()) {
            System.out.print(myStack.pop() + " ");
        }
/*        for (int i = 0; i < 11; i++) {
            System.out.print(myStack.pop() + " ");
        }*/
        System.out.println();
        System.out.println("pop myStack = " + myStack);
    }
}