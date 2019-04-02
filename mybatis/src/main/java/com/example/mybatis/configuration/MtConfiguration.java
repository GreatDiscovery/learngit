package com.example.mybatis.configuration;

import com.example.mybatis.executor.ExecutorType;
import com.example.mybatis.executor.MtExecutor;
import com.example.mybatis.executor.MtSimpleExecutor;
import com.example.mybatis.mapper.MapperRegistory;
import com.example.mybatis.plugin.MtInterceptor;
import com.example.mybatis.plugin.MtInterceptorChain;
import com.example.mybatis.typehandler.MtTypeHandler;
import com.example.mybatis.typehandler.MtTypeHandlerRegistory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author gavin
 * @date 2019/3/29 11:26
 */
public class MtConfiguration {
    private Properties configProperties = new Properties();
    private String configLocation;
    private MtDataSource dataSource;
    private Properties mapperProperties = new Properties();
    private MapperRegistory mapperRegistory = new MapperRegistory();
    private MtInterceptorChain interceptorChain = new MtInterceptorChain();
    private MtTypeHandlerRegistory handlerRegistory = new MtTypeHandlerRegistory();

    public MtConfiguration(String configLocation) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        this.configLocation = configLocation;
        init();
    }

    private void init() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        loadConfigProperties();
        initDataSource();
        loadMapperRegistory();
        initPluginChain();
        initTypeHandler();
    }

    public void loadConfigProperties() {
        if (this.configLocation == null) return;
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(configLocation);
            this.configProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initDataSource() {
        this.dataSource = new MtDataSource(configProperties.getProperty("jdbc.url"), configProperties.getProperty("jdbc.driver"), configProperties.getProperty("jdbc.userName"), configProperties.getProperty("jdbc.passWord"));
    }

    public void loadMapperRegistory() {
        this.scan();
        if (mapperProperties == null) return;
        try {
            mapperRegistory.doLoadMethodSqlMapping(mapperProperties);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void scan() {
        String mapperLocation = configProperties.getProperty("mapperLocation");
        InputStream inputStream = null;
        try {
            inputStream = this.getClass().getClassLoader().getResourceAsStream(mapperLocation);
            this.mapperProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPluginChain() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String pluginStr = configProperties.getProperty("plugin");
        String[] pluginArr = pluginStr.split(",");
        for (String plugin : pluginArr) {
            Class clazz = this.getClass().getClassLoader().loadClass(plugin);
            if (clazz != null) {
                Object o = clazz.newInstance();
                this.interceptorChain.addInterceptor((MtInterceptor) o);
            }
        }
    }

    public void initTypeHandler() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String typeHandlerStr = this.configProperties.getProperty("typeHandler");
        String[] typeHandlerArr = typeHandlerStr.split(",");
        for (String typeHandler : typeHandlerArr) {
            Class clazz = this.getClass().getClassLoader().loadClass(typeHandler);
            if (clazz != null) {
                MtTypeHandler handler = (MtTypeHandler) clazz.newInstance();
                this.handlerRegistory.regist(handler);
            }
        }
    }

    public MapperRegistory getMapperRegistory() {
        return this.mapperRegistory;
    }

    public MtDataSource getDataSource() {
        return dataSource;
    }

    public MtExecutor newExecutor(ExecutorType type) {
        MtExecutor executor = null;
        if (ExecutorType.SIMPLE == type) {
            executor = new MtSimpleExecutor(this);
        }
        return  (MtExecutor) this.interceptorChain.pluginAll(executor);
    }
 }
