package com.example.myspring.support;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author gavin
 * @date 2019/3/28 17:05
 */
public class Handler {
    private Object controller;
    private Method method;
    private Pattern pattern;
    private Map<String, Integer> paramIndexMapping;

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Map<String, Integer> getParamIndexMapping() {
        return paramIndexMapping;
    }

    public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
        this.paramIndexMapping = paramIndexMapping;
    }

    @Override
    public String toString() {
        return "Handler{" +
                "controller=" + controller +
                ", method=" + method +
                ", pattern=" + pattern +
                ", paramIndexMapping=" + paramIndexMapping +
                '}';
    }
}
