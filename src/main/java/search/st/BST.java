package search.st;


/**
 * @author Shane Tang
 * @create 2020-06-14 23:00
 */
public class BST<Key extends Comparable<Key>, Value> extends AbstractST<Key, Value> {

    private BSTNode root;

    private class BSTNode {
        private Key key;
        private Value val;
        private BSTNode left;
        private BSTNode right;
        // 以该结点为根的子树中的结点总数
        private int N;

        public BSTNode(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode x) {
        if (x == null) {
            return 0;
        }else {
            return x.N;
        }
    }

    @Override
    public void put(Key key, Value val) {

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
