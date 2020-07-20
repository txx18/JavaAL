package search;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * @author Shane Tang
 * @create 2020-06-11 20:47
 */
public class SearchTest {

    @Test
    public void testSquentialST() throws IOException {
        SimpleST<String, Integer> obj = new SequentialSearchST<>();
        URL url = SequentialSearchST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
        assert url != null;
        File file = new File(url.getFile());
        FileReader fr = new FileReader(file);
        int len;
        char[] cbuf = new char[1];
//        int b;
        for (int i = 0; (len = fr.read(cbuf)) != -1; i++) {
            String key = new String(cbuf, 0, len);
            obj.put(key, i);
        }
        fr.close();
        for (String key : obj.keys()) {
            System.out.println(key + " " + obj.get(key));
        }
    }

    @Test
    public void testBinarySearchST() throws IOException {
        AbstractST<String, Integer> obj = new BinarySearchST<>();
        URL url = SequentialSearchST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
        assert url != null;
        File file = new File(url.getFile());
        FileReader fr = new FileReader(file);
        int len;
        char[] cbuf = new char[1];
        for (int i = 0; (len = fr.read(cbuf)) != -1; i++) {
            String key = new String(cbuf, 0, len);
            obj.put(key, i);
        }
        fr.close();
//        System.out.println(obj.get("E"));
        for (String key : obj.keys()) {
            System.out.println(key + " " + obj.get(key));
        }
    }

    @Test
    public void testBST() throws IOException {
        BST<String, Integer> obj = new BST<>();
        URL url = SequentialSearchST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
        assert url != null;
        File file = new File(url.getFile());
        FileReader fr = new FileReader(file);
        int len;
        char[] cbuf = new char[1];
        for (int i = 0; (len = fr.read(cbuf)) != -1; i++) {
            String key = new String(cbuf, 0, len);
            obj.put(key, i);
        }
        fr.close();
//        Assert.assertEquals((int)obj.get("E"), 12);
//        Assert.assertEquals(obj.max(), "X");
//        Assert.assertEquals(obj.min(), "A");
//        Assert.assertEquals(obj.floor("G"), "E");
//        Assert.assertEquals(obj.ceiling("G"), "H");
//        Assert.assertEquals(obj.select(3), "H");
//        Assert.assertEquals(obj.rank("H"), 3);

//        obj.deleteMin();
//        obj.deleteMax();
//        obj.delete("E");

//        System.out.println(obj.keys());
//        System.out.println(obj.keys("E", "R"));
        for (String key : obj.keys()) {
            System.out.println(key + " " + obj.get(key));
        }
    }

    @Test
    public void testSeparateChainingHashST() throws IOException {
        SimpleST<String, Integer> obj = new SeparateChainingHashST<>(5);
        URL url = SequentialSearchST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
        assert url != null;
        File file = new File(url.getFile());
        FileReader fr = new FileReader(file);
        int len;
        char[] cbuf = new char[1];
        for (int i = 0; (len = fr.read(cbuf)) != -1; i++) {
            String key = new String(cbuf, 0, len);
            obj.put(key, i);
        }
        fr.close();
        Assert.assertEquals((int) obj.get("E"), 12);
//        System.out.println(obj.get("E"));
//        for (String key : obj.keys()) {
//            System.out.println(key + " " + obj.get(key));
//        }
    }

    @Test
    public void testLinearProbingHashST() throws IOException {
        SimpleST<String, Integer> obj = new LinearProbingHashST<>(16);
        URL url = SequentialSearchST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
        assert url != null;
        File file = new File(url.getFile());
        FileReader fr = new FileReader(file);
        int len;
        char[] cbuf = new char[1];
        for (int i = 0; (len = fr.read(cbuf)) != -1; i++) {
            String key = new String(cbuf, 0, len);
            obj.put(key, i);
        }
        fr.close();
        Assert.assertEquals((int) obj.get("E"), 12);
        Assert.assertNull(obj.get("B"));
//        System.out.println(obj.get("E"));
//        for (String key : obj.keys()) {
//            System.out.println(key + " " + obj.get(key));
//        }
    }
}
