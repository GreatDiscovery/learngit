//package jdk.ovverride.lang;
//
//import sun.nio.ch.Interruptible;
//
//import java.security.AccessControlContext;
//
///**
// * @author gavin
// * @date 2019/3/7 12:00
// */
//public class MyThread implements Runnable {
//
//    private static native void registerNatives();
//
//    static {
//        registerNatives();
//    }
//
//    private volatile String name;
//    private int priority;
//    private MyThread threadQ;
//    private long ettoy;
//
//    private boolean single_step;
//    private boolean daemon = false;
//    private boolean stillborn = false;
//    private Runnable target;
//    private ThreadGroup group;
//    private ClassLoader contextClassLoader;
//    private AccessControlContext inheritedAccessControlContext;
//    private static int threadInitNumber;
//
//    private static synchronized int nextThreadNum() {
//        return threadInitNumber++;
//    }
//
//    ThreadLocal.ThreadLocalMap threadLocals = null;
//    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;
//
//    private long stackSize;
//    private long nativeParkEventPointer;
//    private long tid;
//    private static long threadSeqNumber;
//    private volatile int threadStatus = 0;
//
//    private static synchronized long nextThreadID() {
//        return ++threadInitNumber;
//    }
//
//    volatile Object parkBlocker;
//    private volatile Interruptible blocker;
//    private final Object blockerLock = new Object();
//
//    void blockOn(Interruptible b) {
//        synchronized (blockerLock) {
//            blocker = b;
//        }
//    }
//
//    public final static int MIN_PRIORITY = 1;
//    public final static int NORM_PRIORITY = 5;
//    public final static int MAX_PRIORITY = 10;
//
//    public static native Thread currentThread();
//
//    public static native void yield();
//
//    private void init(ThreadGroup g, Runnable target, String name, long stackSize, AccessControlContext acc,
//                      boolean inheritThreadLocals) {
//        if (name == null)
//            throw new NullPointerException("name cannot be null");
//        this.name = name;
//        Thread parent = currentThread();
//        SecurityManager security = System.getSecurityManager();
//        if (g == null) {
//            if (security != null) {
//                g = security.getThreadGroup();
//            }
//            if (g == null) {
//                g = parent.getThreadGroup();
//            }
//        }
//        g.checkAccess();
//        if (security != null) {
//            if (isCCLOverridden(getClass())) {
//                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
//            }
//
//        }
//        g.addUnstarted();
//
//        this.group = g;
//        this.daemon = parent.isDaemon();
//        this.priority = parent.getPriority();
//        if (security == null || isCCLOverridden(parent.getClass()))
//            this.contextClassLoader = parent.getContextClassLoader();
//        else
//            this.contextClassLoader = parent.getContextClassLoader();
//        this.target = target;
//        setPriority(priority);
//        if (inheritThreadLocals && parent.inheritableThreadLocals != null)
//            this.inheritableThreadLocals = ThreadLocal.createInheriteMap(parent.inheritableThreadLocals);
//        this.stackSize = stackSize;
//        tid = nextThreadID();
//    }
//
//    public MyThread(Runnable target) {
//        init(null, target, "Thread-" + nextThreadNum(), 0, null, false);
//    }
//
//    public synchronized void start() {
//        if (threadStatus != 0)
//            throw new IllegalThreadStateException();
//        group.add(this);
//        boolean started = false;
//        try {
//            start0();
//            started = true;
//        } finally {
//            try {
//                if (!started) {
//                    group.threadStartFailed(this);
//                }
//            } catch (Throwable ignore) {
//
//            }
//        }
//    }
//
//    private native void start0();
//
//    @Override
//    public void run() {
//        if (target != null) {
//            target.run();
//        }
//    }
//}
