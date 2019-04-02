package com.example.mybatis.session;

import com.example.mybatis.configuration.MtConfiguration;

/**
 * @author gavin
 * @date 2019/4/1 10:03
 */
public class MtSqlSessionFactoryBuilder {
    private MtConfiguration configuration;

    public MtSqlSessionFactoryBuilder(MtConfiguration configuration) {
        this.configuration = configuration;
    }

    public MtSqlSessionFactory build() {
        return new MtSqlSessionFactory(configuration);
    }
}
