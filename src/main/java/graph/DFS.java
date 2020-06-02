package graph;

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
public class DFS {

    public static void main(String[] args) {
        // 初始化
        int[][] adjList = {{2, 1, 5}, {0, 2}, {0, 1, 3, 4}, {5, 4, 2}, {3, 2}, {3, 0}};
        Graph graph = new Graph(adjList);
        int s = 0;
        int d = 5;
        // 深度优先搜索，设置搜索树
        DFS obj = new DFS(graph, s);
        // 查找路径
        System.out.println("是否存在到" + d + "的路径：" + obj.hasPathTo(d));
        Stack<Integer> pathTo = obj.pathTo(d);
        while (!pathTo.isEmpty()) {
            System.out.print(pathTo.pop() + " ");
        }
        System.out.println();
        System.out.println("连通节点个数：" + obj.count());
    }


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
                count++;
                // 【关键】设置edgeTo
                edgeTo[j] = i;
                dfs(j);
            }
        }
    }

    public boolean hasPathTo(int i) {
        return marked[i];
    }

    public Stack<Integer> pathTo(int i) {
        if (!hasPathTo(i)) {
            return null;
        }
        // 准备一个栈收集path上的节点
        Stack<Integer> path = new Stack<>();
        // 【关键】遍历edgeTo直到找到起点
        for (int j = i; j != s; j = edgeTo[j]) {
            path.push(j);
        }
        path.push(s);
        return path;
    }

    public int count() {
        return count;
    }
}
