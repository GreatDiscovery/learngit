package meite.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gavin
 * @date 2018/12/18 19:30
 * 线程池
 */
public class TestThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            // 匿名内部类引用外部局部变量，需要加final，以下方式不用
            int tmp = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName:" + Thread.currentThread().getName() + ", i:" + tmp);
                }
            });
        }
        threadPool.shutdown();
    }
}
