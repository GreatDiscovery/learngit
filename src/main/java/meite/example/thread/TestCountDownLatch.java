package meite.example.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author gavin
 * @date 2018/12/17 14:50
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(2);
        System.out.println("开始执行任务");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程1开始执行任务");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程2开始执行任务");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count.countDown();
            }
        }).start();
        // 为了保证任务结束在最后执行，利用信号量来控制
        count.await();
        System.out.println("任务结束");
    }
}
