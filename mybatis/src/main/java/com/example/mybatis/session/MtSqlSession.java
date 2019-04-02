package com.example.mybatis.session;

import com.example.mybatis.configuration.MtConfiguration;
import com.example.mybatis.executor.MtExecutor;
import com.example.mybatis.mapper.MapperData;
import com.example.mybatis.mapper.MtMapperProxy;

import java.lang.reflect.Proxy;

/**
 * @author gavin
 * @date 2019/4/1 10:06
 */
public class MtSqlSession {
    private MtConfiguration configuration;
    // 顶级接口
    private MtExecutor executor;

    public MtSqlSession(MtConfiguration configuration, MtExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public MtConfiguration getConfiguration() {
        return configuration;
    }

    // 这里用代理的目的是什么？应该是要应用上插件，使用增强
    public <T> T getMapper(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MtMapperProxy(this, clazz));
    }

    public <T> T selectOne(MapperData mapperData, Object parameter) {
        return executor.query(mapperData, parameter);
    }
}
