package com.example.mybatis.parameter;


import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author gavin
 * @date 2019/4/1 10:47
 */
public class ParameterHandler {
    private PreparedStatement psmt;

    public ParameterHandler(PreparedStatement psmt) {
        this.psmt = psmt;
    }

    public void setParameters(Object parameter) {
        Object[] parameters = (Object[]) parameter;
        try {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] instanceof Integer) {
                    psmt.setInt(i + 1, (Integer) parameters[i]);
                } else if (parameters[i] instanceof Long) {
                    psmt.setLong(i + 1, (Long) parameters[i]);
                } else if (parameters[i] instanceof String) {
                    psmt.setString(i + 1, (String) parameters[i]);
                } else if (parameters[i] instanceof Boolean) {
                    psmt.setBoolean(i + 1, (Boolean) parameters[i]);
                } else {
                    psmt.setString(i + 1, String.valueOf(parameters[i]));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
