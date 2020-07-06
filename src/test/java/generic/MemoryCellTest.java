package generic;

import org.junit.Test;

public class MemoryCellTest {

    @Test
    public void testPre5() {
        MemoryCell memoryCell = new MemoryCell();
//        memoryCell.write(37);// java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
        memoryCell.write("37");
        String read = (String)memoryCell.read();

        System.out.println(read);
    }

    @Test
    public void testWrapper() {
        MemoryCell m = new MemoryCell();
        m.write(new Integer(37)); // Java5以前没有自动装箱，只能这么做
        Integer wrapperVal = (Integer) m.read();
        int val = wrapperVal.intValue();
        System.out.println("val = " + val);
    }

    @Test
    public void testGeneric() {
        GenericMemoryCell<String> gm = new GenericMemoryCell<>();
//        gm.write(3); // 编译时报错
        gm.write("3");
        System.out.println(gm.read());
    }

    @Test
    public void testAutoBoxing() {
        GenericMemoryCell<Integer> gm = new GenericMemoryCell<>();
        gm.write(3);
        System.out.println(gm.read());
    }
}