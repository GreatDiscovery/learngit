package meite.example.thread;

/**
 * @author gavin
 * @date 2018/12/14 16:33
 */
public class TestVolatile extends Thread{
    public volatile boolean flag = true;

    @Override
    public void run() {
        System.out.println("子线程开始----");
        while (flag) {

        }
        System.out.println("子线程结束----");
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static void main(String[] args) throws Exception{
        TestVolatile t1 = new TestVolatile();
        t1.start();
        // 如果立马改变，flag会立马刷新到主线程中，如果等待，则会丢失更新内容，继续使用本地的内容
        Thread.sleep(3000);
        t1.setFlag(false);
        Thread.sleep(1000);
        System.out.println(t1.flag);
    }
}
