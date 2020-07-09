package collection;

import org.junit.Test;

import java.util.Iterator;

public class MyLinkedListTest {

    MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();

    @Test
    public void addLast() {

        for (int i = 0; i < 10; i++) {
            myLinkedList.add(i);
        }
        System.out.println("addLast myLinkedList = " + myLinkedList);
    }


    @Test
    public void get() {
        addLast();
        System.out.println(myLinkedList.get(0));
        System.out.println(myLinkedList.get(1));
        System.out.println(myLinkedList.get(2));
        System.out.println(myLinkedList.get(3));
        System.out.println(myLinkedList.get(4));
        System.out.println(myLinkedList.get(5));
    }

    @Test
    public void set() {
    }

    @Test
    public void remove() {
        addLast();
        myLinkedList.remove(0);
        System.out.println("myLinkedList = " + myLinkedList);
        myLinkedList.remove(2);
        System.out.println("myLinkedList = " + myLinkedList);
        myLinkedList.remove(myLinkedList.size() - 1);
        System.out.println("myLinkedList = " + myLinkedList);
    }

    @Test
    public void iterator() {
        addLast();
/*        for (Integer item: myLinkedList){

            System.out.println(item + " ");
        }*/
        Iterator<Integer> iterator = myLinkedList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
//            if (i == 4) {
                iterator.remove();
//            }
            System.out.println("iterator myLinkedList = " + myLinkedList);
            i++;
        }
        System.out.println();
        System.out.println("iterator myLinkedList = " + myLinkedList);
    }
}