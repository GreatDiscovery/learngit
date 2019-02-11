package meite.example.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author gavin
 * @date 2019/2/11 15:14
 */
public class CreateTempNode {
    private static final String ADDRES = "127.0.0.1:2181";
    private static final int SESSION_OUTTIME = 2000;
    private static final CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper(ADDRES, SESSION_OUTTIME, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                Event.KeeperState keeperState = watchedEvent.getState();
                Event.EventType eventType = watchedEvent.getType();
                if (Event.KeeperState.SyncConnected == keeperState) {
                    if (Event.EventType.None == eventType) {
                        System.out.println("zk 连接。。。");
                        countDownLatch.countDown();
                    }
                }
            }
        });
        countDownLatch.await();
        String result = zk.create("/gavin_Lasting/node", "node".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(result);
        zk.close();
    }
}
