package search;

/**
 * 红黑二叉查找树（红黑树）
 * @author Shane Tang
 * @create 2020-06-28 15:17
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> extends AbstractST<Key, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private RBNode root;

    private class RBNode {
        Key key;
        Value val;
        RBNode left;
        RBNode right;
        int N;
        boolean color;

        RBNode(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(RBNode x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }
    private boolean isBlack(RBNode x) {
        if (x == null) {
            return false;
        }
        return x.color == BLACK;
    }

    private int size(RBNode x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    private RBNode rotateLeft(RBNode h) {
        RBNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private RBNode rotateRight(RBNode h) {
        RBNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(RBNode h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    @Override
    public void put(Key key, Value val) {
        // 查找key，找到则更新其值，否则为它新建一个结点
        root = put(root, key, val);
        root.color = BLACK;
    }

    private RBNode put(RBNode h, Key key, Value val) {
        // 标准的插入操作，和父结点用红链接相连
        if (h == null) {
            return new RBNode(key, val, 1, RED);
        }
        // 这一段和BST中put()完全相同
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        }else if (cmp > 0) {
            h.right = put(h.right, key, val);
        }else {
            h.val = val;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }

    @Override
    public Value get(Key key) {
        return null;
    }

    @Override
    public Key min() {
        return null;
    }

    @Override
    public Key max() {
        return null;
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

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        return null;
    }


}
