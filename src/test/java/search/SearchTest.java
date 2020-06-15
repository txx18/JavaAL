package search;

import org.junit.Test;
import search.st.AbstractST;
import search.st.BST;
import search.st.BinarySearchST;
import search.st.SquentialST;

import java.io.File;
import java.io.FileNotFoundException;
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
        AbstractST<String, Integer> obj = new SquentialST<>();
        URL url = SquentialST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
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
        URL url = SquentialST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
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
        AbstractST<String, Integer> obj = new BST<>();
        URL url = SquentialST.class.getClassLoader().getResource("SEARCHEXAMPLE.txt");
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
        System.out.println(obj.get("E"));
        System.out.println(obj.max());
        System.out.println(obj.floor("G"));
        System.out.println(obj.ceiling("G"));
//        for (String key : obj.keys()) {
//            System.out.println(key + " " + obj.get(key));
//        }
    }
}
