package org.elasticsearch.common.inject;

public interface Injector {
    <T> T getInstance(Class<T> type);
}
