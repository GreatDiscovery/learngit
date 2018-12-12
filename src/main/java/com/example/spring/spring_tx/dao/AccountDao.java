package com.example.spring.spring_tx.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author gavin
 * @date 2018/12/12 14:00
 */
@Repository
public class AccountDao {

    private JdbcTemplate template;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        System.out.println(dataSource.toString());
        Assert.notNull(dataSource, "dataSource不能为null");
        template = new JdbcTemplate(dataSource);
    }

    public Double selectAccount(String name) {
        String sql = "select money from t_account where name = ?";
        Double money = template.queryForObject(sql, new Object[]{name}, Double.class);
        return money;
    }

    public void updateForOut(String out, Double money) throws Exception {
        String sql = "update t_account set money = money-? where name = ?";
        template.update(sql, money, out);
    }

    public void updateForIn(String in, Double money) throws Exception {
        String sql = "update t_account set money = money+? where name = ?";
        template.update(sql, money, in);
    }

}
