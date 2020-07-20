package graph;

/**
 * 加权quick-union算法
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-06 20:21
 */
public class UFWeightQuickUnionImpl implements UF {
    /**
     * 每个连通分量（树）的大小
     */
    private int[] treeSize;

    /**
     * 以触点为索引的数组
     */
    private int[] parent;
    /**
     * 连通分量个数
     */
    private int count;


    public UFWeightQuickUnionImpl(int N) {
        this.parent = new int[N];
        for (int i = 0; i < N; i++) {
            this.parent[i] = i;
        }
        this.count = N;
        this.treeSize = new int[N];
        for (int i = 0; i < N; i++) {
            this.treeSize[i] = 1;
        }
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
        // 总是小树并到大树上去
        if (treeSize[q] <= treeSize[p]) {
            parent[qRoot] = pRoot;
            treeSize[p] += treeSize[q];
        }else {
            parent[pRoot] = qRoot;
            treeSize[q] += treeSize[p];
        }

        count--;
    }

    /**
     * 找出连通分量的祖先代号
     * 【最优】路径压缩
     *
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        // 直到它的代号是它自己
        while (parent[p] != p ) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
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
