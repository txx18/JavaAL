package graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Stack;

/**
 * 图处理的用例
 *
 * @author Shane Tang
 * @create 2020-07-03 20:19
 */
public class TestGraph {

    @Test
    public void testSearch() {
        // 初始化图
        Graph G = new Graph(new In("tinyG.txt"));
//        System.out.println(G);
        // 起点
        int s = Integer.parseInt("0");
        Search search = new DFS(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();
        if (search.count() != G.V()) {
            System.out.print("NOT ");
        }
        System.out.println("connected");
    }

    @Test
    public void testDFS() {
        Graph G = new Graph(new In("tinyG.txt"));
//        System.out.println(G);
        int s = Integer.parseInt("0");
        DFS paths = new DFS(G, s);
        System.out.println("与s连通的顶点总数：" + paths.count());
        for (int v = 0; v < G.V(); v++) {
            if (paths.hasPathTo(v)) {
                System.out.print(s + " to " + v + ": ");
                for (int x: paths.pathTo(v)) {
                    if (x == s) {
                        System.out.print(x);
                    }else {
                        System.out.print("-" + x);
                    }
                }
            }
            System.out.println();
        }
    }

    @Test
    public void testBFS() {
        Graph G = new Graph(new In("tinyG.txt"));
//        System.out.println(G);
        int s = Integer.parseInt("0");
        BFS paths = new BFS(G, s);
        System.out.println("与s连通的顶点总数：" + paths.count());
        for (int v = 0; v < G.V(); v++) {
            if (paths.hasPathTo(v)) {
                System.out.print(s + " to " + v + ": ");
                for (int x: paths.pathTo(v)) {
                    if (x == s) {
                        System.out.print(x);
                    }else {
                        System.out.print("-" + x);
                    }
                }
            }
            System.out.println();
        }
    }



}
