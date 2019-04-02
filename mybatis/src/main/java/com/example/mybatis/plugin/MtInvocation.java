package com.example.mybatis.plugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author gavin
 * @date 2019/3/29 16:20
 */
public class MtInvocation {

    private Object target;
    private Method method;
    private Object[] args;

    public MtInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object procced() throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(this.target, this.args);
    }
}
