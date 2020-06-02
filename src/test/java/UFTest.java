import graph.UF;
import graph.UFQuickFindImpl;
import graph.UFQuickUnionImpl;
import graph.UFWeightQuickUnionImpl;
import org.junit.Test;

public class UFTest {

    public static void main(String[] args) {
//        UF uf = new UFQuickFindImpl(10);
//        UF uf = new UFQuickUnionImpl(10);
        UF uf = new UFWeightQuickUnionImpl(10);

        uf.union(4, 3);
        uf.union(3, 8);
        uf.union(6, 5);
        uf.union(9, 4);
        uf.union(2, 1);
        uf.union(8, 9);
        uf.union(5, 0);
        uf.union(7, 2);
        uf.union(6, 1);
        uf.union(1, 0);
        uf.union(6, 7);


        System.out.println(uf.find(5));
        System.out.println(uf.find(9));

        System.out.println(uf.isConnected(0, 2));
        System.out.println(uf.isConnected(0, 8));

        System.out.println("连通分量数 = " + uf.count());
    }

    @Test
    public void union() {
    }

    @Test
    public void find() {
    }

    @Test
    public void isConnected() {
    }

    @Test
    public void count() {
    }
}