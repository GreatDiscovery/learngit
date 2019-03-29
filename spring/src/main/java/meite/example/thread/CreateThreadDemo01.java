package meite.example.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gavin
 * @date 2018/12/11 12:04
 */
public class CreateThreadDemo01 extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1);
                System.out.println("id:" + getId() + ",run , i = " + i);
            }
            } catch (Exception e){
                e.printStackTrace();
            }
    }
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        CreateThreadDemo01 t1 = new CreateThreadDemo01();
        // 设置为守护线程
        t1.setDaemon(true);
        t1.start();
        try {
            // t1先执行，当前线程让给其他线程
            t1.join();
            for (int i = 0; i < 50; i++) {
                Thread.sleep(1);
                System.out.println("id:" + Thread.currentThread().getId() + ",main , i = " + i);
            }
            // 主线程死掉不影响用户线程

            Thread.currentThread().stop();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
