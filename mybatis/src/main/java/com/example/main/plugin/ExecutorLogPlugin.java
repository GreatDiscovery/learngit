package com.example.main.plugin;

import com.example.mybatis.mapper.MapperData;
import com.example.mybatis.plugin.MtInterceptor;
import com.example.mybatis.plugin.MtInvocation;
import com.example.mybatis.plugin.MtPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * 日志输出插件
 *
 * @author gavin
 * @date 2019/4/1 19:29
 */
public class ExecutorLogPlugin implements MtInterceptor {
    private final Logger logger = LoggerFactory.getLogger(ExecutorLogPlugin.class);

    public ExecutorLogPlugin() {
    }

    @Override
    public Object intercept(MtInvocation invocation) {
        MapperData mapperData = (MapperData) invocation.getArgs()[0];
        Object[] parameter = (Object[]) invocation.getArgs()[1];
        logger.info("ExecutorLogPlugin is in processing....");
        logger.info("mapperData is :" + mapperData);
        for (int i = 0; i < parameter.length; i++) {
            logger.info("parameter " + i + " is　:" + parameter[i]);
        }
        try {
            return invocation.procced();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object plugin(Object var1) {
        return MtPlugin.wrap(var1, this);
    }
}
