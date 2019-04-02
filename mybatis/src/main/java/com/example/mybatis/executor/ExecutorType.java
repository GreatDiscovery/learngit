package com.example.mybatis.executor;

/**
 * @author gavin
 * @date 2019/4/1 10:31
 */
public enum ExecutorType {
    SIMPLE,
    REUSE,
    BATCH;

    ExecutorType() {
    }
}
