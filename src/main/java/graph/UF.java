package graph;

/**
 * @author Shane Tang
 * @version V1.0
 * @create 2020-04-06 20:14
 */
public interface UF {

    void union(int p, int q);

    int find(int p);

    default boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    int count();
}
