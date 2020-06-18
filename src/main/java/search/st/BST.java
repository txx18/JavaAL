package search.st;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Shane Tang
 * @create 2020-06-14 23:00
 */
public class BST<Key extends Comparable<Key>, Value> extends AbstractST<Key, Value> {

    public BSTNode root;

    public class BSTNode {
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

        @Override
        public String toString() {
            return "BSTNode{" +
                    "key=" + key +
                    ", val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", N=" + N +
                    '}';
        }
    }

    public BST() {

    }

    public BST(BSTNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "BST{" +
                "root=" + root +
                '}';
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
        BSTNode tar = get(root, key);
        if (tar == null) {
            return null;
        }
        return tar.val;
    }

    private BSTNode get(BSTNode cur, Key key) {
        if (cur == null) {
            return null;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp < 0) {
            return get(cur.left, key);
        } else if (cmp > 0) {
            return get(cur.right, key);
        } else {
            return cur;
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
        // 防御只有根结点的情况
        if (cur.left == null) {
            return cur;
        }
        return min(cur.left);
    }

    @Override
    public Key max() {
        BSTNode max = max(root);
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
        // 触底反弹
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
        // 否则递归右子树
        // 加上之前的递归左子树，左右子树都有递归操作了
        BSTNode t = floor(cur.right, key);
        // 如果右子树中不存在<=key的结点，那么返回该根结点；如果右子树中存在<=key的结点，就返回那个<=key的结点
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
        return rank(root, key);
    }

    private int rank(BSTNode cur, Key key) {
        if (cur == null) {
            return -1;
        }
        int cmp = key.compareTo(cur.key);
        if (cmp == 0) {
            return size(cur.left);
        }else if (cmp < 0) {
            return rank(cur.left, key);
        }else {
            // 递归右边之前加上了左边和自己的计数
            return 1 + size(cur.left) + rank(cur.right, key);
        }
    }

    /**
     * 查找排名第k的key，从第0个开始
     * @param k
     * @return
     */
    @Override
    public Key select(int k) {
        BSTNode select = select(root, k);
        if (select == null) {
            return null;
        }
        return select.key;
    }

    private BSTNode select(BSTNode cur, int k) {
        if (cur == null) {
            return null;
        }
        // 类似切分的找排名第k的数
        // 如果左子树的节点数=k，那么根结点是第k名
        int t = size(cur.left);
        if (t == k) {
            return cur;
        }
        // 如果左子树的节点数>k，那么递归地在左子树查找第k名
        else if (t > k) {
            return select(cur.left, k);
        }
        // 如果做叔叔的节点数<k，那么递归地在右子树查找第 k-t-1 名
        else {
            return select(cur.right, k - t - 1);
        }
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        return keys(root, lo, hi, queue);
    }

    private Iterable<Key> keys(BSTNode cur, Key lo, Key hi, Queue<Key> queue) {
        // 修改中序遍历
        if (cur == null) {
            return queue;
        }
        int cmpLo = lo.compareTo(cur.key);
        int cmpHi = hi.compareTo(cur.key);
        // 如果lo < 当前，才需要递归左子树，否则不需要递归左子树，什么都不用干
        if (cmpLo < 0) {
            keys(cur.left, lo, hi, queue);
        }
        // 只有要找的lo<=当前<=hi，才加入当前
        if(cmpLo <= 0 && cmpHi >= 0) {
            queue.offer(cur.key);
        }
        // 递归右子树
        if (cmpHi > 0) {
            keys(cur.right, lo, hi, queue);
        }
        return queue;
    }

    /**
     * TODO BST最难的操作
     * @param key
     */
    @Override
    public void delete(Key key) {
        // 递归会返回root
        root = delete(root, key);
    }

    private BSTNode delete(BSTNode cur, Key key) {
        if (cur == null) {
            return null;
        }
        // 找到待删除结点，递归【完成操作】，这个顶层递归的思想非常关键
        int cmp = key.compareTo(cur.key);
        // 这两个if结合了deleteMin和deleteMax
        if (cmp < 0) {
            cur.left = delete(cur.left, key);
        }else if (cmp > 0) {
            cur.right = delete(cur.right, key);
        }
        // 必须有这个else，表示只有cmp == 0才执行这一段
        else {
            // cmp == 0递归找到待删除结点之后
            // 又开始删除操作，这又是递归
            // base case 又是结合
            if (cur.left == null) {
                return cur.right;
            }
            if (cur.right == null) {
                return cur.left;
            }
            // 用t记录toDelNode，用t记录就不要动t
            BSTNode t = cur;
            // 记录其【后继结点】，cur的后继是它右子树的最小结点
            cur = min(t.right);
            // 删除其【后继结点】，返回右子树根结点。此时右子树已经完成了找到、重新连接、更新数值
            // 待删除结点连接右子树（等号左边）
            cur.right = deleteMin(t.right);
            // 待删除的结点连接左子树（用之前的记录）
            cur.left = t.left;
        }
        // 更新数值
        cur.N = size(cur.left) + size(cur.right) + 1;
        return cur;
    }


    private BSTNode getNextNode(BSTNode cur) {
        if (cur.right == null) {
            return cur.left;
        }
        return getNextNode(cur.right);
    }

    /**
     * 重写方法
     */
    @Override
    public void deleteMin() {
/*        if (root.left == null) {
            root = root.right;
            return;
        }*/
        deleteMin(root);
    }

    /**
     * 感觉还是人家写的优雅
     * @param cur
     * @return
     */
    private BSTNode deleteMin(BSTNode cur) {
/*        if (cur.left.left == null) {
            cur.left = cur.left.right;
            return root;
        }
        // 不对，如何设置N呢
        cur.N = size(cur.left) + size(cur.right) + 1;
        return deleteMin(cur.left);*/
        // base case 左子树递归到尽头，【返回】cur.right。这里cur代入的是cur.left，相当于
        if (cur.left == null) {
            return cur.right;
        }
        // 这行代码非常关键非常优雅
        // 等号右边是递归左子树（左子树可能有更小的），等号左边是用cur.left连接新结点
        cur.left = deleteMin(cur.left);
        cur.N = size(cur.left) + size(cur.right) + 1;
        // 其余情况【返回】cur，最终返回root
        return cur;
    }

    @Override
    public void deleteMax() {
        deleteMax(root);
    }

    private BSTNode deleteMax(BSTNode cur) {
        if (cur.right == null) {
            return cur.left;
        }
        cur.right = deleteMax(cur.right);
        cur.N = size(cur.left) + size(cur.right) + 1;
        return cur;
    }
}
