package com.example.mybatis.mapper;

/**
 * @author gavin
 * @date 2019/3/29 11:58
 */
public class MapperData {
    private String sql;
    private Class type;

    public MapperData(String sql, Class type) {
        this.sql = sql;
        this.type = type;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MapperData{" +
                "sql='" + sql + '\'' +
                ", type=" + type +
                '}';
    }
}
