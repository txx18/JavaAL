package tree;


/**
 * 二叉树查找
 *
 * @author Shane Tang
 * @create 2020-06-14 23:00
 */
public class BST<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        BSTNode<Integer> root = new BSTNode<>(1);
        root.left = new BSTNode<>(2);
        root.right = new BSTNode<>(3);

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < 10; i++) {
            bst.insert(i);
        }
        System.out.println(bst.contains(3));
        System.out.println(bst.findMin());
        System.out.println(bst.findMax());
        bst.remove(bst.findMax());
    }

    public BSTNode root;

    private static class BSTNode<T> {
        T element;

        BSTNode<T> left;

        BSTNode<T> right;

        BSTNode(T x) {
            this(x, null, null);
        }

        public BSTNode(T x, BSTNode<T> lt, BSTNode<T> rt) {
            element = x;
            left = lt;
            right = rt;
        }
    }

    public BST() {
        root = null;
    }

    /**************************公共接口***********************/

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    public T findMin() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        // TODO
        return (T) findMin(root).element;
    }

    public T findMax() {
        if (isEmpty()) {
            throw new NullPointerException();
        }
        return (T) findMax(root).element;
    }

    public void insert(T x) {
        root = insert(x, root);
    }


    public void remove(T x) {
        root = remove(x, root);
    }

    public void printTree() {

    }

    /**************************私有实现***********************/

    private boolean contains(T x, BSTNode<T> cur) {
        // 首先测试是否是空树
        if (cur == null) {
            return false;
        }
        int cmp = x.compareTo(cur.element);
        // 把最不可能的情况放在最后
        if (cmp < 0) {
            return contains(x, cur.left);
        } else if (cmp > 0) {
            return contains(x, cur.right);
        } else {
            return true;
        }
    }

    /**
     * 用递归实现
     *
     * @param t
     * @return
     */
    private BSTNode<T> findMin(BSTNode<T> t) {
/*        // （我）
        if (cur.left != null) {
            return findMin(cur.left);
        }
        return cur;*/

        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);

    }

    /**
     * 用循环实现
     *
     * @param t
     * @return
     */
    private BSTNode<T> findMax(BSTNode<T> t) {
/*        while (t.right != null) {
            t = t.right;
        }
        return t;*/
/*        // 递归同理
        if (t == null) {
            return null;
        }else if (t.right == null) {
            return t;
        }
        return findMax(t.right);*/

        if (t == null) {
            return null;
        }
        while (t.right != null) {
            t = t.right;
        }
        return t;
    }

    /**
     * @param x
     * @param cur
     * @return
     */
    private BSTNode<T> insert(T x, BSTNode<T> cur) {
        if (cur == null) {
            return new BSTNode<>(x);
        }
        int cmp = x.compareTo(cur.element);
        // 把最不可能的情况放在最后
        if (cmp < 0) {
            // 这是递归连接
            cur.left = insert(x, cur.left);
        } else if (cmp > 0) {
            cur.right = insert(x, cur.right);
        } else {
            // do nothing
//            cur.element = x;
        }
        return cur;
    }

    /**
     * @param x
     * @param t
     * @return
     */
    private BSTNode<T> remove(T x, BSTNode<T> t) {
        if (t == null) {
            return null;
        }
        int cmp = x.compareTo(t.element);
        if (cmp < 0) {
            t.left = remove(x, t.left);
        } else if (cmp > 0) {
            t.right = remove(x, t.right);
        } else {
            if (t.left != null && t.right != null) {
//                t.element = findMin(t.right).element;
//                t.right = remove(t.element, t.right);
                t.element = findMax(t.left).element;
                t.left = remove(t.element, t.left);
            } else {
                t = (t.left != null) ? t.left : t.right;
            }
        }
        return t;
    }
}
