package meite.example.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author gavin
 * @date 2019/2/21 17:06
 * 测试park和unpark方法
 */
public class TestPark {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("child thread begin park!");
                // 没有许可证被挂起
                LockSupport.park();
                System.out.println("chile thread unpark!");
            }
        });
        thread.start();
        Thread.sleep(1000);
        System.out.println("main thread begin unpark");
        // 给Thread许可证并唤醒
//        LockSupport.unpark(thread);
        thread.interrupt();
    }
}
