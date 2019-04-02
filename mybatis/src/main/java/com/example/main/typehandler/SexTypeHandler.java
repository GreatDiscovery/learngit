package com.example.main.typehandler;

import com.example.main.common.SEX;
import com.example.mybatis.typehandler.MtTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author gavin
 * @date 2019/4/1 19:11
 */
public class SexTypeHandler implements MtTypeHandler<SEX> {
    private static final Logger logger = LoggerFactory.getLogger(SexTypeHandler.class);

    @Override
    public void setParameter(PreparedStatement var1, int var2, SEX var3, JdbcType var4) {

    }

    @Override
    public SEX getResult(ResultSet var1, String var2) {
        logger.info("SexTypeHandler is in processing...");
        try {
            if (SEX.MALE.getSexType().equals(var1.getInt(var2))) {
                return SEX.MALE;
            } else {
                return SEX.FEMALE;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SEX getResult(ResultSet var1, int var2) {
        return null;
    }

    @Override
    public Type getJavaType() {
        return ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
    }
}
