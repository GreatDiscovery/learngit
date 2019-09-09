package org.elasticsearch.bootstrap;

import org.elasticsearch.node.Node;
import sun.misc.Version;

import java.nio.file.Path;
import java.util.concurrent.CountDownLatch;

public class Bootstrap {
    private static volatile Bootstrap INSTANCE;
    private volatile Node node;
    private final Thread keepAliveThread;
    private final CountDownLatch keepAliveLatch = new CountDownLatch(1);

    Bootstrap() {
        keepAliveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                keepAliveLatch.await();
            }
        }, "elasticsearch[keepAlive/]" + Version.CURRENT + "]");
        keepAliveThread.setDaemon(false);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                keepAliveLatch.countDown();
            }
        });
    }
    static void init(final boolean foregroun,
                     final Path pidFile,
                     final boolean quiet,
                     final Environment initialEnv) {
        BootStrapInfo.init();
        INSTANCE = new Bootstrap();

        final SecureSettings keystore = loadSecureSettings(initialEnv);
        final Environment environment = createEnvironment(foregroun, pidFile, keystore, initialEnv.settings());
        INSTANCE.start();
    }

    public void start() {
        node.start();
        keep
    }
}
