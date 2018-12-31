package meite.example.jvm;

import java.util.Arrays;
import java.util.Map;

/**
 * @author gavin
 * @date 2018/12/31 15:00
 */
public class TestGC {
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("垃圾回收finalize函数");
    }

    public static void main(String[] args) {
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + Arrays.toString(entry.getValue()));
        }
        TestGC test1 = new TestGC();
        test1 = new TestGC();
        System.gc();
    }
}
