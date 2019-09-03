package com.example.mybatis.plugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author gavin
 * @date 2019/3/29 16:18
 */
public class MtInterceptorChain {
    private List<MtInterceptor> interceptors = new ArrayList<>();

    public void addInterceptor(MtInterceptor interceptor) {
        this.interceptors.add(interceptor);
    }

    public List<MtInterceptor> getInterceptors() {
        return Collections.unmodifiableList(this.interceptors);
    }

    public Object pluginAll(Object target) {
        for (MtInterceptor var1 : interceptors) {
            target = var1.plugin(target);
        }
        return target;
    }
}
