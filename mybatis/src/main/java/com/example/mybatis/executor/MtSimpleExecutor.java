package com.example.mybatis.executor;

import com.example.mybatis.configuration.MtConfiguration;
import com.example.mybatis.mapper.MapperData;
import com.example.mybatis.statement.StatementHandler;

/**
 * @author gavin
 * @date 2019/4/1 10:34
 */
public class MtSimpleExecutor implements MtExecutor{
    private MtConfiguration configuration;

    public MtSimpleExecutor(MtConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T query(MapperData mapperData, Object parameter) {
        StatementHandler statementHandler = new StatementHandler(configuration);
        return statementHandler.query(mapperData, parameter);
    }
}
