package collection.imitJdk;

/**
 * @author Shane Tang
 * @create 2020-01-29 11:14
 */
public class MyLinkedList<E> extends MyAbstractList<E> {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;

    public MyLinkedList() {

    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E element, Node<E> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(E e) {
        final Node<E> l = this.last;
        final Node<E> newNode = new Node<>(l, e, null);
        this.last = newNode;
        // 第一次添加，就把头指针指向它
        if (l == null) {
            this.first = newNode;
//            return true; // 这样是错误的，size没加，卫语句不是解决一切问题的，除非它确实该return了
        }else {
            // 第二次及以后添加，连到末尾
            l.next = newNode;
        }
        this.size++;
        modCount++;
        return true;
    }

/*    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (! it.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }*/

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<E> getFirst() {
        return first;
    }

    public void setFirst(Node<E> first) {
        this.first = first;
    }

    public Node<E> getLast() {
        return last;
    }

    public void setLast(Node<E> last) {
        this.last = last;
    }

    public E get(int idx) {
        checkElementIdx(idx);
        return node(idx).element;
    }

    private Node<E> node(int idx) {
        // 如果在左半边，从头遍历
        if (idx < (size >> 1)){
            Node<E> f = this.first;
            for (int i = 0; i < idx; i++) {
                f = f.next;
            }
            return f;
        }
        // 如果在右半边，从尾遍历
        else {
            Node<E> l = this.last;
            for (int i = size - 1; i > idx; i--){
                l = l.prev;
            }
            return l;
        }
    }

    private void checkElementIdx(int idx) {
        if (idx < 0 || idx >= size){
            throw new IndexOutOfBoundsException("Index: "+ idx +", Size: "+size);
        }
    }

    @Override
    public String toString() {
        Node<E> cur = this.first;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (cur != null) {
            sb.append(cur.element).append(", ");
            cur = cur.next;
        }
        if (cur.next == null) {
            sb.append(cur.element);
            sb.append("]");
        }
        return sb.toString();
    }
}
