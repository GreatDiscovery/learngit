package org.elasticsearch.common.inject;

public class InjectorImpl implements Injector {
    @Override
    public <T> T getInstance(Class<T> type) {
        return getProvider(type).get();
    }

    public <T> Provider<T> getProvider(Class<T> type) {
        return getProvider(Key.get(type));
    }
}
