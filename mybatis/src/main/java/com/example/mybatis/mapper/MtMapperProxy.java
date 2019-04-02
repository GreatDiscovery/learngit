package com.example.mybatis.mapper;

import com.example.mybatis.session.MtSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author gavin
 * @date 2019/4/1 10:14
 */
public class MtMapperProxy implements InvocationHandler {
    private MtSqlSession mtSqlSession;
    private Class mapperInterface;

    public MtMapperProxy(MtSqlSession mtSqlSession, Class mapperInterface) {
        this.mtSqlSession = mtSqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperData mapperData = this.mtSqlSession.getConfiguration().getMapperRegistory().getMethodSqlMapping().get(mapperInterface.getName() + "." + method.getName());
        if (mapperData != null) {
            return this.mtSqlSession.selectOne(mapperData, args);
        }
        return method.invoke(proxy, args);
    }
}
