package com.example.mybatis.session;

import com.example.mybatis.configuration.MtConfiguration;
import com.example.mybatis.executor.ExecutorType;
import com.example.mybatis.executor.MtExecutor;

/**
 * @author gavin
 * @date 2019/4/1 10:05
 */
public class MtSqlSessionFactory {
    private MtConfiguration configuration;

    public MtSqlSessionFactory(MtConfiguration configuration) {
        this.configuration = configuration;
    }

    private MtSqlSession openSessionFromDataSource(ExecutorType executorType) {
        MtExecutor executor = this.configuration.newExecutor(executorType);
        MtSqlSession sqlSession = new MtSqlSession(configuration, executor);
        return sqlSession;
    }

    public MtSqlSession openSession() {
        return openSessionFromDataSource(ExecutorType.SIMPLE);
    }
}
