package collection;

import collection.imitJdk.MyArrayList;
import collection.imitJdk.MyLinkedList;
import collection.imitJdk.MyStack;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author Shane Tang
 * @create 2020-01-08 22:14
 */
public class CollectionTest {

    @Test
    public void jdkArrayListTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("national ");
        arrayList.add("university ");
        arrayList.add("of ");
    }

    @Test
    public void myArrayListTest() {
        MyArrayList<String> myArrayList = new MyArrayList<>();
        boolean add1 = myArrayList.add("national ");
        myArrayList.add("university ");
        myArrayList.add("of ");
        myArrayList.add("science ");
        System.out.println("myArrayList = " + myArrayList);

        String getEle = myArrayList.get(0);
        System.out.println("ele = " + getEle);

        String rmEle = myArrayList.romove(1);
        System.out.println("rmEle = " + rmEle);
        System.out.println("myArrayList = " + myArrayList);
    }

    @Test
    public void jdkLinkedListTest() {
        List<String> linkedList = new LinkedList<>();
        System.out.println(linkedList.size());
        linkedList.add("national");
        System.out.println(linkedList.size());
        linkedList.add("university");
        System.out.println(linkedList.size());
        System.out.println("linkedList = " + linkedList);
    }

    @Test
    public void myLinkedListTest() {
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        boolean add1 = myLinkedList.add("t");
        boolean add2 = myLinkedList.add("a");
        String e1 = myLinkedList.get(1);
        System.out.println("e1 = " + e1);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.getSize());

    }

    @Test
    public void jdkStackTest() {
        Stack stack = new Stack();
        stack.push("s");
        stack.push("h");
        Object e1 = stack.pop();
        System.out.println("e1 = " + e1);
    }

    @Test
    public void myStackTest() {
        MyStack<String> myStack = new MyStack<>();
        myStack.push("s");
        myStack.push("h");
        System.out.println("peek = " + myStack.peek());
        System.out.println(myStack);
        Object e1 = myStack.pop();
        System.out.println("e1 = " + e1);
        System.out.println(myStack);
    }
}
