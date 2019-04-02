package com.example.mybatis.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author gavin
 * @date 2019/3/29 11:56
 */
public class MapperRegistory {
    private Logger logger = LoggerFactory.getLogger(MapperRegistory.class);
    private Map<String, MapperData> methodSqlMapping = new HashMap();

    public void doLoadMethodSqlMapping(Properties mapperProperties) throws ClassNotFoundException {
        Enumeration propertyNames = mapperProperties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String key = (String) propertyNames.nextElement();
            String value = mapperProperties.getProperty(key);
            String sql = value.substring(0, value.indexOf("#"));
            String t = value.substring(value.indexOf("#") + 1, value.length());
            // 将名字变为Class对象
            Class type = this.getClass().getClassLoader().loadClass(t);
            this.methodSqlMapping.put(key, new MapperData(sql, type));
            logger.info("In loading methodSqlMapping------sql: "+sql+" and type: "+type);
        }
    }

    public Map<String, MapperData> getMethodSqlMapping() {
        return methodSqlMapping;
    }
}
