package graph;

/**
 * 算法1.5 Union-find
 *
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-06 17:26
 */
public class UFQuickFindImpl implements UF {



    /**
     * 以触点为索引的数组
     */
    private int[] id;
    /**
     * 连通分量个数
     */
    private int count;

    public UFQuickFindImpl(int N) {
        this.id = new int[N];
        for (int i = 0; i < N; i++) {
            this.id[i] = i;
        }
        this.count = N;
    }

    /**
     * 把q的代号全部改为p的
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (isConnected(p, q)){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            id[i] = id[i] == qID ? pID : id[i];
        }
        count--;
    }

    /**
     * find很快
     * @param p
     * @return
     */
    @Override
    public int find(int p) {
        return id[p];
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
