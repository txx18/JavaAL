package graph;

import collection.ResizingArrayStack;

import java.util.Stack;

/**
 * 算法4.1 深度优先搜索
 * <p>
 * 1、连通性
 * 2、起点到所有节点的路径
 * 3、起点有多少个连通节点
 *
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-05 11:38
 */
public class DFS implements Search, Paths{

    private Graph G;
    /**
     * marked[i] i顶点是否调用dfs
     * i顶点和起点是否连通
     */
    private boolean[] marked;
    /**
     * edgeTo[i]: 从起点到i顶点的已知路径的最后一个顶点，也就是i前一个顶点
     */
    private int[] edgeTo;
    /**
     * 与起点连通的节点个数
     */
    private int count;
    /**
     * 起点
     */
    private int s;


    public DFS(Graph G, int s) {
        this.G = G;
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        // 调用dfs
        dfs(s);
    }

    /**
     * 深度优先搜索
     *
     * @param i
     */
    private void dfs(int i) {
        // 【关键】上来先mark了
        marked[i] = true;
        for (Integer j : G.adj(i)) {
            if (!marked[j]) {
                this.count++;
                // 【关键】设置edgeTo
                edgeTo[j] = i;
                // 递归
                dfs(j);
            }
        }
    }

    @Override
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    @Override
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        // 准备一个栈收集path上的节点（要保证Iterator迭代是逆序，而系统的Stack不是逆序的）
        ResizingArrayStack<Integer> path = new ResizingArrayStack<>();
        // 【关键】遍历edgeTo直到找到起点
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    @Override
    public boolean marked(int v) {
        return marked[v];
    }

    @Override
    public int count() {
        return this.count;
    }
}
