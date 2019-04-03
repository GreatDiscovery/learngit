package com.example.mybatis.result;

import com.example.main.common.SEX;
import com.example.mybatis.typehandler.MtTypeHandler;
import com.example.mybatis.typehandler.MtTypeHandlerRegistory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author gavin
 * @date 2019/4/1 11:01
 */
public class ResultHandler {
    private Logger logger = LoggerFactory.getLogger(ResultHandler.class);
    private Class type;
    private ResultSet resultSet;

    public ResultHandler(Class type, ResultSet resultSet) {
        this.type = type;
        this.resultSet = resultSet;
    }

    public <E> E handle() {
        Object resultObject = new DefaultObjectFactory().create(type);
        try {
            if (resultSet.next()) {
                for (Field field : resultObject.getClass().getDeclaredFields()) {
                    setValue(resultObject, field, resultSet);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (E) resultObject;
    }

    public void setValue(Object resultObject, Field field, ResultSet resultset) throws NoSuchMethodException, SQLException, InvocationTargetException, IllegalAccessException {
        Method method = type.getMethod("set" + upperCapital(field.getName()), field.getType());
        method.invoke(resultObject, getResult(field, resultSet));
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }

    public Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type = field.getType();
        if (Integer.class == type) {
            return rs.getInt(field.getName());
        } else if (String.class == type) {
            return rs.getString(field.getName());
        } else if (Long.class == type) {
            return rs.getLong(field.getName());
        } else if (SEX.class == type) {
            MtTypeHandler typeHandler = MtTypeHandlerRegistory.TYPE_HANDLER_MAP.get(type);
            return typeHandler.getResult(resultSet, field.getName());
        } else {
            return rs.getString(field.getName());
        }
    }
}
