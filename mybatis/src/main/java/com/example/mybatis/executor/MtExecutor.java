package com.example.mybatis.executor;

import com.example.mybatis.mapper.MapperData;

/**
 * @author gavin
 * @date 2019/4/1 10:08
 */
public interface MtExecutor {
    <T> T query(MapperData mapperData, Object parameter);
}
