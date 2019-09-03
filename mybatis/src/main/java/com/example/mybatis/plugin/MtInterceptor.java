package com.example.mybatis.plugin;

/**
 * @author gavin
 * @date 2019/3/29 16:19
 */
public interface MtInterceptor {
    Object intercept(MtInvocation var1);

    Object plugin(Object var1);
}
