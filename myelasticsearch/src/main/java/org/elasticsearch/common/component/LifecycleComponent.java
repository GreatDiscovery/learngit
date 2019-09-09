package org.elasticsearch.common.component;

import java.io.Closeable;

public interface LifecycleComponent extends Closeable {
    Lifecycle.State lifecycleState();
    void addLifecycleListener(LifecycleListener listener);
    void removeLifecycleListener(LifecycleListener listener);
    void start();
    void stop();
}
