package graph;

import collection.ResizingArrayStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先搜索
 * 解决单点最短路径问题
 *
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-05 16:21
 */
public class BFS implements Search, Paths{

/*    public static void main(String[] args) {
        // 初始化
        int[][] adjList = {{2, 1, 5}, {0, 2}, {0, 1, 3, 4}, {5, 4, 2}, {3, 2}, {3, 0}};
        Graph graph = new Graph(adjList);
        int s = 0;
        int d = 4;
        BFS obj = new BFS(graph, s);
        // 查找路径
        System.out.println("是否存在到" + d + "的路径：" + obj.hasPathTo(d));
        Stack<Integer> pathTo = obj.pathTo(d);
        while (!pathTo.isEmpty()) {
            System.out.print(pathTo.pop() + " ");
        }
    }*/

    private Graph G;

    private boolean[] marked;

    private int[] edgeTo;

    private final int s;
    /**
     * 与起点连通的节点个数
     */
    private int count;

    public BFS(Graph G, int s) {
        this.G = G;
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        bfs(s);
    }

    public void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        // 初始：起点先进队列
        marked[s] = true;
        queue.offer(s);
        while (!queue.isEmpty()) {
            // 【关键】出队列一个
            int i = queue.poll();
            this.count++;
            for (int j : G.adj(i)) {
                if (!marked[j]) {
                    marked[j] = true;
                    edgeTo[j] = i;
                    // 把邻接节点都拉进队列
                    queue.offer(j);
                }
            }
        }
    }

    @Override
    public boolean hasPathTo(int i) {
        return marked[i];
    }

    @Override
    public Iterable<Integer> pathTo(int i) {
        if (!hasPathTo(i)) {
            return null;
        }
        // 准备一个栈收集path上的节点
        ResizingArrayStack<Integer> path = new ResizingArrayStack<>();
        // 遍历edgeTo直到找到起点
        for (int j = i; j != s; j = edgeTo[j]) {
            path.push(j);
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
