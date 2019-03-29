package meite.example.jvm;

import java.text.DecimalFormat;

/**
 * @author gavin
 * @date 2018/12/30 16:25
 */
public class JvmParams {
    public static void main(String[] args) throws InterruptedException {
        byte[] bytes1 = new byte[1 * 1024 * 1024];
        System.out.println("bytes1分配了1M内存");
        jvmInfo();
        Thread.sleep(3000);
        byte[] bytes2 = new byte[4 * 1024 * 1024];
        System.out.println("bytes2分配了4M内存");
        jvmInfo();
    }
    static private String toM(long maxMemory) {
        float num = (float)maxMemory / (1024 * 1024);
        DecimalFormat df = new DecimalFormat("0.00");
        String s = df.format(num);
        return s;
    }
    public static void jvmInfo() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory:" + maxMemory + "," + toM(maxMemory) + "M");

        long freeMem = Runtime.getRuntime().freeMemory();
        System.out.println("freeMemory:" + toM(freeMem));

        long totalMem = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory:" + toM(totalMem));
    }
}
