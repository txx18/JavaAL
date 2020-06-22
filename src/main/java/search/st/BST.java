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
     * @param x 任意结点
     * @param key
     * @param val
     * @return 当前put操作的根结点
     */
    private BSTNode put(BSTNode x, Key key, Value val) {
        if (x == null) {
            return new BSTNode(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        /*递归之前的代码可以想象成沿着树向下走*/
        if (cmp < 0) {
            // x.left = ... 左子树连接（注意这是在递归中）
            // 这个写法最早在put就开始用
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            // 右子树连接
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        /*递归之后的代码可以想象成沿着树向上爬*/
        // 增加路径上每个结点计数器的值。对任意结点x，size(x) = size(x.left) + size(x.right) + 1
        x.N = size(x.left) + size(x.right) + 1;
        // 最终返回整棵树的根结点
        return x;
    }

    @Override
    public Value get(Key key) {
        BSTNode tar = get(root, key);
        if (tar == null) {
            return null;
        }
        return tar.val;
    }

    private BSTNode get(BSTNode x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x;
        }
    }

    @Override
    public Key min() {
        BSTNode min = min(root);
        // 书上缺少这一处理
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
        // 书上缺少这一处理
        if (cur.left == null) {
            return cur;
        }
        // 如果左子树非空，那么树的最小键就是左子树的最小键
        return min(cur.left);
    }

    /**
     * 参照min()
     * @return
     */
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

    /**
     * floor是找用户指定key的向下取整
     * 并不是找某个指定Node的前驱，同样ceiling也不是找某个指定Node的后继
     * @param x
     * @param key
     * @return
     */
    private BSTNode floor(BSTNode x, Key key)
    {
        // 没找到，触底反弹
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        // 找到，最终是要返回一个结点
        if (cmp == 0) {
            return x;
        }
        // 如果比当前小，肯定在左子树，递归左子树
        if (cmp < 0) {
            return floor(x.left, key);
        }
        // 否则递归右子树
        // 上面是return所以可以不用else，相当于if (cmp >= 0)
        BSTNode t = floor(x.right, key);
        // 如果右子树中不存在<=key的结点，那么返回该根结点
        if (t == null) {
            return x;
        }
        // 如果右子树中存在<=key的结点，就返回那个<=key的结点
        else {
            return t;
        }
    }

    /**
     * 参照floor()
     * @param key
     * @return
     */
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
        BSTNode t = ceiling(cur.left, key);
        if (t == null) {
            return cur;
        }else {
            return t;
        }
    }

    /**
     * 查找排名，查找第k个key，从第0个开始
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

    private BSTNode select(BSTNode x, int k) {
        if (x == null) {
            return null;
        }
        // 类似切分的找排名第k的数
        // 如果左子树的节点数=k，那么根结点是第k名
        int leftSize = size(x.left);
        if (leftSize == k) {
            return x;
        }
        // 如果左子树的节点数>k，那么递归地在左子树查找第k名
        else if (leftSize > k) {
            return select(x.left, k);
        }
        // 如果左子树的节点数<k，那么递归地在右子树查找第 k-t-1 名
        else {
            return select(x.right, k - leftSize - 1);
        }
    }

    @Override
    public int rank(Key key) {
        return rank(root, key);
    }

    /**
     * 计算排名
     * 又是一类十分优雅的递归
     * @param x
     * @param key
     * @return 以x为根结点的子树中小于x.key的键的数量
     */
    private int rank(BSTNode x, Key key) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return size(x.left);
        }else if (cmp < 0) {
            return rank(x.left, key);
        }else {
            // 递归右子树
            // 加上了左边和自己的计数
            return 1 + size(x.left) + rank(x.right, key);
        }
    }

    /**
     * 重写AbstractST的方法
     */
    @Override
    public void deleteMin() {
        deleteMin(root);
    }

    /**
     * 感觉还是人家写的优雅
     * @param x
     * @return
     */
    private BSTNode deleteMin(BSTNode x) {
        // base case 非常优雅，检查左子树为空，【返回】x.right这个x的后继结点，准备跨越删除
        if (x.left == null) {
            return x.right;
        }
        // 这行代码非常关键非常优雅  这里 x 代入的是 x.left，相当于 base case 判断 x.left.left
        // 等号右边是递归左子树（左子树可能有更小的），
        // 等号左边是用cur.left连接新结点，跨越删除
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        // 其余情况【返回】cur，最终返回root
        return x;
    }

    /**
     * 参照deleteMin()
     */
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

    /**
     * TODO BST最难的操作
     * @param key
     */
    @Override
    public void delete(Key key) {
        // 递归会返回root
        root = delete(root, key);
    }

    /**
     * Hibbard核心思想是找后继结点替换待删除结点，还要保持二叉查找树的结构
     * @param x
     * @param key
     * @return
     */
    private BSTNode delete(BSTNode x, Key key) {
        if (x == null) {
            return null;
        }
        // 找到待删除结点，递归【完成操作】
        // 这个顶层递归的思想非常关键，在递归程序中，不要有“分步”的思想（分步：比如“找到”就先调用get）
        int cmp = key.compareTo(x.key);
        // 这两个if结合了deleteMin和deleteMax
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }else if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        // 必须有这个else，表示只有cmp == 0才执行这一段
        else {
            // cmp == 0递归找到待删除结点之后
            // 又开始删除操作，这又是递归
            // base case 又是结合deleteMin的base case
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            // 用t记录toDelNode，用t记录就不要动t
            BSTNode t = x;
            // 把 x 的【后继结点】赋给 x（其实是用后继结点替换），x 的后继是它右子树的最小结点min(t.right)
            x = min(t.right);
            // 跨越删除其后继结点，返回右子树根结点。右子树deleteMin已经完成了找到、重新连接、更新数值
            // 待删除结点连接右子树（等号左边）
            x.right = deleteMin(t.right);
            // cur连接左子树（用之前的记录）
            x.left = t.left;
        }
        // 更新数值
        x.N = size(x.left) + size(x.right) + 1;
        return x;
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
}
