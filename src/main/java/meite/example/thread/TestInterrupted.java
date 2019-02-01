package meite.example.thread;

import org.junit.Test;

/**
 * @author gavin
 * @date 2019/1/28 17:17
 */
public class TestInterrupted {
    @Test
    public void test01() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + "hello");
                }
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println("main thread interrupt thread");
        thread.interrupt();
        thread.join();
        System.out.println("main is over");
    }

    @Test
    public void test02() {
        double a = 1.0;
        double b = 0.9;
        System.out.println(a - b);
    }
}
