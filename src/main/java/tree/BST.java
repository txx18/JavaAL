package tree;


/**
 * 二叉树查找（基于二叉查找树）
 * @author Shane Tang
 * @create 2020-06-14 23:00
 */
public class BST<Key extends Comparable<? super Key>> {

    public BSTNode root;

    private class BSTNode<Key extends Comparable<? super Key>> {
        Key key;
        BSTNode<Key> left;
        BSTNode<Key> right;
    }
}
