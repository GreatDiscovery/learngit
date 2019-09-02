package org.elasticsearch.common.component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractLifecycleComponent implements LifecycleComponent {
    protected final Lifecycle lifecycle = new Lifecycle();
    private final List<LifecycleListener> listeners = new CopyOnWriteArrayList<>();
    @Override
    public void start() {
        if (!lifecycle.canMoveToStarted()) {
            return;
        }
        for (LifecycleListener listener : listeners) {
            listener.beforeStart();
        }
        doStart();
        lifecycle.moveToStarted();
    }

    @Override
    public void stop() {

    }
}
