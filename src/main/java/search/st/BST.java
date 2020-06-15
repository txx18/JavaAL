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
        } else {
            return x.N;
        }
    }

    @Override
    public void put(Key key, Value val) {
        // 传参为root
        root = put(root, key, val);
    }

    /**
     * @param cur 任意结点
     * @param key
     * @param val
     * @return 当前put操作的根结点
     */
    private BSTNode put(BSTNode cur, Key key, Value val) {
        if (cur == null) {
            return new BSTNode(key, val, 1);
        }
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            // cur.left = ... 左子树连接
            cur.left = put(cur.left, key, val);
        } else if (cmp > 0) {
            // 右子树连接
            cur.right = put(cur.right, key, val);
        } else {
            cur.val = val;
        }
        // 增加路径上每个结点计数器的值。对任意结点x，size(x) = size(x.left) + size(x.right) + 1
        cur.N = size(cur.left) + size(cur.right) + 1;
        // 最终返回整棵树的根结点
        return cur;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(BSTNode cur, Key key) {
        if (cur == null) {
            return null;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            return get(cur.left, key);
        } else if (cmp > 0) {
            return get(cur.right, key);
        } else {
            return cur.val;
        }
    }

    @Override
    public Key min() {
        BSTNode min = min(root);
        if (min == null) {
            return null;
        }
        return min.key;
    }

    private BSTNode min(BSTNode cur) {
        if (cur == null) {
            return null;
        }
        if (cur.left == null) {
            return cur;
        }
        return min(cur.left);
    }

    @Override
    public Key max() {
        BSTNode max = min(root);
        if (max == null) {
            return null;
        }
        return max.key;
    }

    private BSTNode max(BSTNode cur) {
        if (cur == null) {
            return null;
        }
        if (cur.right == null) {
            return cur;
        }
        return max(cur.right);
    }

    @Override
    public Key floor(Key key) {
        BSTNode floor = floor(root, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }

    private BSTNode floor(BSTNode cur, Key key) {
        if (cur == null) {
            return null;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp == 0) {
            return cur;
        }
        // 如果比当前小，肯定在左子树，递归左子树
        if (cmp < 0) {
            return floor(cur.left, key);
        }
        // 否则递归右子树。
        BSTNode t = floor(cur.right, key);
        // 如果右子树中不存在<=key的结点，那么返回该根结点；只有当右子树中存在<=key的结点时，才返回那个<=key的结点
        if (t == null) {
            return cur;
        } else {
            return t;
        }
    }

    @Override
    public Key ceiling(Key key) {
        BSTNode ceil = ceiling(root, key);
        if (ceil == null) {
            return null;
        }
        return ceil.key;
    }

    private BSTNode ceiling(BSTNode cur, Key key) {
        if (cur == null) {
            return null;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp == 0) {
            return cur;
        }else if (cmp > 0) {
            return ceiling(cur.right, key);
        }
        // 否则递归左子树
        BSTNode 临时 = ceiling(cur.left, key);
        if (临时 == null) {
            return cur;
        }else {
            return 临时;
        }
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
