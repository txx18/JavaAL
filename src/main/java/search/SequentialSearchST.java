package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 顺序查找（基于无序链表）
 * @author Shane Tang
 * @create 2020-06-10 8:55
 */
public class SequentialSearchST<Key, Value> extends SimpleST<Key, Value>{

    private class Node{

        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private Node head;

    public SequentialSearchST() {

    }

    public SequentialSearchST(Node head) {
        this.head = head;
    }

    @Override
    public void put(Key key, Value val) {
        // 如果是前插：判断cur != null
        // 如果是后插：判断cur.next != null 循环体顶多执行到倒数第二个节点
        for (Node cur = head; cur != null; cur = cur.next) {
            if (key.equals(cur.key)) {
                cur.val = val;
                return;
            }
        }
        // 没有找到，此时cur到最后一个节点，新节点插到最后
//        cur.next = new Node<>(key, val);
        // 书上是新节点插到最前
        head = new Node(key, val, head);
    }

    @Override
    public Value get(Key key) {
        Node cur = head;
        if (cur == null) {
            return null;
        }
/*        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                return cur.val;
            }
            cur = cur.next;
        }*/
        for (cur = head; cur != null; cur = cur.next) {
            if (key.equals(cur.key)) {
                return cur.val;
            }
        }
        return null;
    }

    @Override
    void delete(Key key) {
    }

    @Override
    int size() {
        return 0;
    }


    public Key min() {
        if (head == null) {
            return null;
        }
        return head.key;
    }

    public Key max() {
        if (head == null) {
            return null;
        }
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur.key;
    }

    /**
     * 实际上是简单符号表的实现
     * @return
     */
    @Override
    public Iterable<Key> keys() {
        if (head == null) {
            return null;
        }
        List<Key> keys = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            keys.add(cur.key);
            cur = cur.next;
        }
        return keys;
    }
}
