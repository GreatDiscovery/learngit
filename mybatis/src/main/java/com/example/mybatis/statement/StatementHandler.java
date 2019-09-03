package com.example.mybatis.statement;

import com.example.mybatis.configuration.MtConfiguration;
import com.example.mybatis.configuration.MtDataSource;
import com.example.mybatis.executor.ExecutorType;
import com.example.mybatis.mapper.MapperData;
import com.example.mybatis.parameter.ParameterHandler;
import com.example.mybatis.result.ResultHandler;

import java.sql.*;

/**
 * 语句集处理
 *
 * @author gavin
 * @date 2019/4/1 10:37
 */
public class StatementHandler {
    private MtConfiguration configuration;

    public StatementHandler(MtConfiguration configuration) {
        this.configuration = configuration;
    }

    public <E> E query(MapperData mapperData, Object parameter) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        try {
            conn = getConnection();
            // 语句处理
            preparedStatement = conn.prepareStatement(mapperData.getSql());
            // 参数处理
            ParameterHandler parameterHandler = new ParameterHandler(preparedStatement);
            parameterHandler.setParameters(parameter);
            preparedStatement.execute();
            // 结果集处理
            ResultHandler resultHandler = new ResultHandler(mapperData.getType(), preparedStatement.getResultSet());
            return (E) resultHandler.handle();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        MtDataSource dataSource = configuration.getDataSource();
        Class.forName(dataSource.getDriver());
        return DriverManager.getConnection(dataSource.getUrl(), dataSource.getUserName(), dataSource.getPassword());
    }
}
