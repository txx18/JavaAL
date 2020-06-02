package graph;

import java.util.*;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-05 09:17
 */
public class Graph {

    public static void main(String[] args) {
//        int[][] adjList = {{2, 4}, {1, 3}, {2, 4}, {1, 3}};
        int[][] adjList = {{2, 1, 5}, {0, 2}, {0, 1, 3, 4}, {5, 4, 2}, {3, 2}, {3, 0}};
        Graph graph = new Graph(adjList);
        System.out.println(graph);
    }

    /**
     * 顶点数目
     */
    private int V;
    /**
     * 边数目
     */
    private int E;
    /**
     * 邻接表
     */
//    private Set<Integer>[] adj;
    private List<Integer>[] adj;

    /**
     *
     * @param V
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        // 初始化由 构成的数组，new出来再强转
//        adj = (HashSet<Integer>[]) new HashSet[V];
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int i = 0; i < V; i++) {
//            adj[i] = new HashSet<>();
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 用二维数组邻接表构造图
     *
     * @param adj
     */
    public Graph(int[][] adj) {
        // 读取V并将图初始化，起点设置为0
        this(adj.length);
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                addEdge(i, adj[i][j]);
            }
        }
    }

    public void addEdge(int i, int j) {
        adj[i].add(j);
//        adj[w].add(v);
        this.E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E / 2;
    }

    /**
     * @param i
     * @return 集合都可以用List或Iterable接口接收
     */
    public Iterable<Integer> adj(int i) {
        return adj[i];
    }

    @Override
    public String toString() {
        String s = V() + " vertices, " + E() + " edges\n";
        for (int i = 0; i < V; i++) {
            s += i + ": ";
            for (int j : this.adj[i]) {
                s += j + " ";
            }
            s += "\n";
        }
        return s;
    }
}
