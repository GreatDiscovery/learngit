package com.example.mybatis.plugin;

import com.example.mybatis.executor.MtExecutor;
import sun.java2d.opengl.OGLContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Properties;

/**
 * @author gavin
 * @date 2019/4/1 19:33
 */
public class MtPlugin implements InvocationHandler {
    private Object targer;
    private MtInterceptor interceptor;

    public MtPlugin(Object targer, MtInterceptor interceptor) {
        this.targer = targer;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.interceptor.intercept(new MtInvocation(targer, method, args));
    }

    public static Object wrap(Object target, MtInterceptor interceptor) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), new Class[]{MtExecutor.class}, new MtPlugin(target, interceptor));
    }
}
