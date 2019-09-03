package meite.example.thread;

/**
 * 死锁
 *
 * @author gavin
 * @date 2018/12/14 15:45
 */
public class DeadLock implements Runnable {

    private Object object = new Object();
    private static int tickets = 100;
    public static boolean flag = true;

    @Override
    public void run() {
        if (flag) {
            while (true) {
                synchronized (object) {
                    sale();
                }
            }
        } else {
            while (true) {
                sale();
            }
        }
    }

    public synchronized void sale() {
        synchronized (object) {
            if (tickets > 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ",出售" + (100 - tickets + 1) + "票");
                tickets--;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        DeadLock deadLock = new DeadLock();
        Thread t1 = new Thread(deadLock, "窗口①");
        Thread t2 = new Thread(deadLock, "窗口②");
        t1.start();
        Thread.sleep(40);
        flag = false;
        t2.start();
    }
}
