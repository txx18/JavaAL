package collection;

/**
 *
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

    /*
    * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
circularDeque.insertLast(1);			        // 返回 true
circularDeque.insertLast(2);			        // 返回 true
circularDeque.insertFront(3);			        // 返回 true
circularDeque.insertFront(4);			        // 已经满了，返回 false
circularDeque.getRear();  				// 返回 2
circularDeque.isFull();				        // 返回 true
circularDeque.deleteLast();			        // 返回 true
circularDeque.insertFront(4);			        // 返回 true
circularDeque.getFront();				// 返回 4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/design-circular-deque
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*
* [[4],[9],[],[],[],[],[],[6],[5],[9],[],[6]]
* [null,true,true,-1,-1,-1,false,true,true,true,9,true]
    * */

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3); // 设置容量大小为3
        System.out.println(circularDeque.insertLast(1));
        System.out.println(circularDeque.insertLast(2));
        System.out.println(circularDeque.insertFront(3));
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getRear());
        System.out.println(circularDeque.isFull());
        System.out.println(circularDeque.deleteLast());
        System.out.println("circularDeque = " + circularDeque);
        System.out.println(circularDeque.insertFront(4));
        System.out.println(circularDeque.getFront());

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
        if (isEmpty()) {
            return -1;
        }
        if (front == elements.length - 1) {
            // get的时候不能移动front指针
            return elements[0];
        }else {
            return elements[front + 1];
        }
    }

    /**
     * Get the last item from the deque.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
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

    /**
     * TODO toString OutOfMemoryError
     * @return
     */
    @Override
    public String toString() {
//        StringBuffer sb = new StringBuffer();
        String res = "";
        if (size() == 0) {
            return "null";
        }
        for (int i = front; i != rear; i++) {
            if (i == elements.length - 1) {
                i = moveToArrFront();
            }
//            sb.append(elements[i]).append(" ");
            res += elements[i] + " ";
        }
//        sb.append(elements[rear]);
        res += elements[rear];
//        return sb.toString();
        return res;
    }

    private int moveToArrRear() {
        return elements.length - 1;
    }

    private int moveToArrFront() {
        return 0;
    }
}


