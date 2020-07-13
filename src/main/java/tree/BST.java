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
/*        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 10);
            System.out.println("random = " + random);
            bst.insert(random);
        }*/
        for (int i = 0; i < 10; i++) {
            bst.insert(i);
        }
/*        int[] phone = {1, 5, 1, 1, 6, 4, 3, 4, 9, 5, 7};
        for (int num : phone) {
            bst.insert(num);
        }*/
        bst.printTree();
        System.out.println();
        System.out.println("height = " + bst.height());
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
        if (isEmpty()) {
            System.out.println("Empty tree");
        }
        printTree(root);

    }

    public int height() {
        if (isEmpty()) {
            return -1;
        }
        return height(root);
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
        // （我）
        if (t.left != null) {
            return findMin(t.left);
        }
        return t;

/*        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);*/

    }

    /**
     * 用循环实现
     *
     * @param t
     * @return
     */
    private BSTNode<T> findMax(BSTNode<T> t) {
        while (t.right != null) {
            t = t.right;
        }
        return t;
        // 递归同理
/*        if (t == null) {
            return null;
        }else if (t.right == null) {
            return t;
        }
        return findMax(t.right);*/

/*        if (t == null) {
            return null;
        }
        while (t.right != null) {
            t = t.right;
        }
        return t;*/
    }

    /**
     * @param x
     * @param t
     * @return
     */
    private BSTNode<T> insert(T x, BSTNode<T> t) {
        if (t == null) {
            return new BSTNode<>(x);
        }
        int cmp = x.compareTo(t.element);
        if (cmp < 0) {
            t.left = insert(x, t.left);
        } else if (cmp > 0) {
            t.right = insert(x, t.right);
        } else {
            ;
        }
        // 最终返回root
        return t;
    }

    /**
     * @param x
     * @param t
     * @return
     */
    private BSTNode<T> remove(T x, BSTNode<T> t) {
        if (t == null) {
            return t;
        }
        int cmp = x.compareTo(t.element);
        if (cmp < 0) {
            t.left = remove(x, t.left);
        } else if (cmp > 0) {
            t.right = remove(x, t.right);
        } else {
            if (t.left != null && t.right != null) {
                t.element = findMin(t.right).element;
                return remove(t.element, t.right);
            } else {
                t = (t.left != null) ? t.left : t.right;
            }
        }
        return t;
    }

    private void printTree(BSTNode<T> x) {
        if (x == null) {
            return;
        }
        printTree(x.left);
        System.out.print(x.element + " ");
        printTree(x.right);
    }

    private int height(BSTNode<T> t) {
        // （我）
/*        if (t == null) {
            return -1;
        }
        int lh = height(t.left) + 1;
        int rh = height(t.right) + 1;
        return Math.max(lh, rh);*/
        // （书）
        if (t == null) {
            return -1;
        }
        return 1 + Math.max(height(t.left), height(t.right));
    }
}
