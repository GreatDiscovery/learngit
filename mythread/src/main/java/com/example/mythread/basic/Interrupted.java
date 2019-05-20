package com.example.mythread.basic;

/**
 * 中断interrupt方法
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + "hello");
                }
            }
        });
        thread.start();
        Thread.sleep(1000);

        System.out.println("main thread interrupt thread");
        // 并不会立马中断
        thread.interrupt();
        // 主线程让出了时间片
        thread.join();
        System.out.println("main is over");
    }
}
