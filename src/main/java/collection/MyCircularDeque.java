package collection;

/**
 * TODO deleteLast OutOfMemoryError
 * @author Shane Tang
 * @create 2020-07-11 14:43
 */
public class MyCircularDeque {
    /**
     * Your MyCircularDeque object will be instantiated and called as such:
     * MyCircularDeque obj = new MyCircularDeque(k);
     * boolean param_1 = obj.insertFront(value);
     * boolean param_2 = obj.insertLast(value);
     * boolean param_3 = obj.deleteFront();
     * boolean param_4 = obj.deleteLast();
     * int param_5 = obj.getFront();
     * int param_6 = obj.getRear();
     * boolean param_7 = obj.isEmpty();
     * boolean param_8 = obj.isFull();
     */

    public static void main(String[] args) {
        MyCircularDeque myCircularDeque = new MyCircularDeque(4);
        for (int i = 0; i < 5; i++) {
            myCircularDeque.insertFront(i);
//            System.out.println(myCircularDeque);
        }
        System.out.println("insertFront myCircularDeque = " + myCircularDeque);
        for (int i = 0; i < 3; i++) {
            myCircularDeque.deleteLast();
//            System.out.println(myCircularDeque);
        }
        System.out.println("deleteLast myCircularDeque = " + myCircularDeque);
    }

//    private T[] elements;
    private int[] elements;

    private int front;

    private int rear;

    private int size;

    /**
     * Initialize your data structure here. Set the size of the deque to be k.
     */
    public MyCircularDeque(int k) {
        if (k <= 0) {
            k = 1;
        }
//        elements = ((T[]) new Object[k]);
        elements = new int[k];
        front = elements.length - 1;
        rear = front;
        size = 0;
    }

    /**
     * Adds an item at the front of Deque. Return true if the operation is successful.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        // 先填再前移
        elements[front] = value;
        if (front == 0) {
            front = moveToArrRear();
        }else {
            front--;
        }
        size++;
        return true;
    }

    /**
     * Adds an item at the rear of Deque. Return true if the operation is successful.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        // 先后移再填
        rear++;
        if (rear == elements.length) {
            rear = moveToArrFront();
        }
        elements[rear] = value;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of Deque. Return true if the operation is successful.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        if (front == elements.length - 1) {
            front = moveToArrFront();
        }else {
            front++;
        }
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of Deque. Return true if the operation is successful.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        if (rear == 0) {
            rear = moveToArrRear();
        }else {
            rear--;
        }
        size--;
        return true;
    }

    /**
     * Get the front item from the deque.
     */
    public int getFront() {
        if (front == elements.length - 1) {
            front = moveToArrFront();
        }
        return elements[front];
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        return elements[rear];
    }

    /**
     * Checks whether the circular deque is empty or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full or not.
     */
    public boolean isFull() {
        return size == elements.length;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (size() == 0) {
            return "null";
        }
        for (int i = front + 1; i != rear; i++) {
            if (i == elements.length) {
                i = moveToArrFront();
            }
            sb.append(elements[i]).append(" ");
        }
        sb.append(elements[rear]);
        return sb.toString();
    }

    private int moveToArrRear() {
        return elements.length - 1;
    }

    private int moveToArrFront() {
        return 0;
    }
}


