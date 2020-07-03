package graph;

/**
 * 路径的API
 *
 * @author Shane Tang
 * @create 2020-07-03 21:45
 */
public interface Paths {
    /**
     * 是否存在s到v的路径
     * @param v
     * @return
     */
    boolean hasPathTo(int v);

    /**
     * s到v的路径，如果不存在则返回null
     * @param v
     * @return
     */
    Iterable<Integer> pathTo(int v);
}
