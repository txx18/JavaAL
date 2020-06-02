package graph;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-06 20:21
 */
public class UFQuickUnionImpl implements UF {

    /**
     * 以触点为索引的数组
     */
    private int[] id;
    /**
     * 连通分量个数
     */
    private int count;

    public UFQuickUnionImpl(int N) {
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
        }
        this.count = N;
    }

    /**
     * 分别找到祖先代号后，把其中一个祖先的代号改成另一个的
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        id[qRoot] = pRoot;
        count--;
    }

    /**
     * 找出连通分量的祖先代号
     *
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        // 直到它的代号是它自己
        while (id[p] != p ) {
            p = id[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }
}
