package com.example.mybatis.typehandler;

import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author gavin
 * @date 2019/3/29 16:40
 */
public interface MtTypeHandler<T> {
    void setParameter(PreparedStatement var1, int var2, T var3, JdbcType var4);
    T getResult(ResultSet var1, String var2);
    T getResult(ResultSet var1, int var2);
    Type getJavaType();
}
