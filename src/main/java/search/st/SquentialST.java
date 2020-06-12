package search.st;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 算法3.1 顺序查找（基于无序链表）
 * @author Shane Tang
 * @create 2020-06-10 8:55
 */
public class SquentialST<Key extends Comparable<Key>, Value> extends AbstractST<Key, Value> {

    public static void main(String[] args) throws IOException {
        SquentialST<String, Integer> obj = new SquentialST<>();
        URL url = SquentialST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
        assert url != null;
        File file = new File(url.getFile());
        FileReader fr = new FileReader(file);
        int len;
        char[] cbuf = new char[1];
//        int b;
        for (int i = 0; (len = fr.read(cbuf)) != -1; i++) {
            String key = new String(cbuf, 0, len);
            obj.put(key, i);
        }
        fr.close();
        for (String key : obj.keys()) {
            System.out.println(key + " " + obj.get(key));
        }

    }

    private static class Node<Key extends Comparable<Key>, Value> {

        Key key;
        Value val;
        Node<Key, Value> next;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    private Node<Key, Value> head;

    public SquentialST() {

    }

    public SquentialST(Node<Key, Value> head) {
        this.head = head;
    }

    @Override
    public void put(Key key, Value val) {
        if (head == null) {
            head = new Node<>(key, val);
            return;
        }
        Node<Key, Value> cur = head;
/*        while (cur.next != null) {
            if (key.compareTo(cur.key) == 0) {
                cur.val = val;
                return;
            }
            cur = cur.next;
        }*/
        // cur.next != null 顶多执行到倒数第二个节点
        for (cur = head; cur.next != null; cur = cur.next) {
            if (key.compareTo(cur.key) == 0) {
                cur.val = val;
                return;
            }
        }
        // 此时cur到最后一个节点，新节点插到最后
        cur.next = new Node<>(key, val);
        this.size++;
    }

    @Override
    public Value get(Key key) {
        Node<Key, Value> cur = head;
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
            if (key.compareTo(cur.key) == 0) {
                return cur.val;
            }
        }
        return null;
    }

    @Override
    public Key min() {
        if (head == null) {
            return null;
        }
        return head.key;
    }

    @Override
    public Key max() {
        if (head == null) {
            return null;
        }
        Node<Key, Value> cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur.key;
    }

    @Override
    public Key floor(Key key) {
        return null;
    }

    @Override
    public Key ceiling(Key key) {
        return null;
    }

    @Override
    public int rank(Key key) {
        return 0;
    }

    @Override
    public Key select(int k) {
        return null;
    }

    /**
     * 第一个lo和最后一个hi之间的keys
     * 但是这里给的参数是key，ST的key可以重复，指代本身是不明确的，默认为headNode到tailNode
     * 实际上是简单符号表的实现
     * @param lo
     * @param hi
     * @return
     */
    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        if (head == null) {
            return null;
        }
        List<Key> keys = new ArrayList<>();
        Node<Key, Value> cur = head;
        while (cur != null) {
            keys.add(cur.key);
            cur = cur.next;
        }
        return keys;
    }
}
