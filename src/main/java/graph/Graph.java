package graph;

import collection.Bag;
import edu.princeton.cs.algs4.In;

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
    private final int V;
    /**
     * 边数目
     */
    private int E;
    /**
     * 邻接表
     */
//    private Set<Integer>[] adj;
//    private List<Integer>[] adj;
    /**
     * 书上用的Bag数组作为底层表示邻接表
     */
    private Bag<Integer>[] adj;
    /**
     *
     * @param V
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        // 初始化由 构成的数组，new出来再强转
//        adj = (HashSet<Integer>[]) new HashSet[V];
//        adj = (LinkedList<Integer>[]) new LinkedList[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
//            adj[i] = new HashSet<>();
//            adj[i] = new LinkedList<>();
            adj[i] = new Bag<Integer>();
        }
    }

    public Graph(In in) {
        // 读取V并将图初始化
        this(in.readInt());
        // 读取E
        int E = in.readInt();
        // 添加边
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    /**
     * （我）用二维数组邻接表构造图
     *
     * @param adj
     */
    public Graph(int[][] adj) {
        // 读取V并将图初始化，起点设置为0
        this(adj.length);
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[i].length; j++) {
                // 二维数组手动表示了平行边，所以只用每个只添加一条边；但是tinyG.txt不会手动表示平行边
                addSingleEdge(i, adj[i][j]);
            }
        }
    }

    private void addSingleEdge(int v, int w) {
        adj[v].add(w);
        this.E++;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        this.E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * @param v
     * @return 集合都可以用List或Iterable接口接收
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        String s = V() + " vertices, " + E() + " edges\n";
        for (int v = 0; v < V; v++) {
            s += v + ": ";
            for (int w : this.adj[v]) {
                s += w + " ";
            }
            s += "\n";
        }
        return s;
    }
}
