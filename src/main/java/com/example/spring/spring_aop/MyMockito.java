package com.example.spring.spring_aop;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 利用动态代理来模拟mocktio
 *
 * @author gavin
 * @date 2019/1/23 16:29
 */

class MethodInfo {
    private MyCGLibInterceptor interceptor;
    private Method method;
    private Object[] args;

    public MethodInfo(MyCGLibInterceptor interceptor, Method method, Object[] args) {
        this.interceptor = interceptor;
        this.method = method;
        this.args = args;
    }

    @Override
    public int hashCode() {
        return interceptor.hashCode() + method.hashCode() + Arrays.hashCode(args);
    }
    @Override
    public boolean equals(Object other) {
        if (other instanceof MethodInfo) {
            MethodInfo otherMethodInfo = (MethodInfo) other;
            return interceptor.equals(otherMethodInfo.interceptor) && method.equals(otherMethodInfo.method) && Arrays.equals(args, otherMethodInfo.args);
        }
        return false;
    }

    @Override
    public String toString() {
        return "MethodInfo{" +
                "interceptor=" + interceptor +
                ", method=" + method +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}

class MockInjector {
    private MethodInfo methodInfo;

    MockInjector(MethodInfo methodInfo) {
        this.methodInfo = methodInfo;
    }

    public void thenReturn(final Object mockResult) {
        MyMockito.MOCKED_METHODS.put(methodInfo, mockResult);
    }
}

class MyCGLibInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodInfo key = new MethodInfo(this, method, objects);
        boolean hasMocked = MyMockito.MOCKED_METHODS.containsKey(key);
        if (!hasMocked) {
            System.out.println("Initializing the mock for " + key.toString());
            return key;
        } else {
            System.out.println("Returns the mock result:");
            return MyMockito.MOCKED_METHODS.get(key);
        }
    }

    public Object getInstance(final Class<?> t) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(t);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}

public class MyMockito {
    public static final Map<MethodInfo, Object> MOCKED_METHODS = new HashMap<>();

    public static MockInjector when(Object methodCall) {
        return new MockInjector((MethodInfo) methodCall);
    }

    private static MyCGLibInterceptor getInstanceptor() {
        return new MyCGLibInterceptor();
    }

    public static void main(String[] args) {
        List<String> myList = (List<String>) getInstanceptor().getInstance(List.class);
        MyMockito.when(myList.get(0)).thenReturn("hello, 我获得了返回值");
        System.out.println("myList.get(0)=" + myList.get(0));
    }
}
