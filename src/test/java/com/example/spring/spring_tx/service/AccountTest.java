package com.example.spring.spring_tx.service;

import com.example.spring.spring_tx.dao.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author gavin
 * @date 2018/12/12 14:38
 */

@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringRunner.class)
public class AccountTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void testTranfer() {
        try {
            accountService.transfer("sam", "jack", 100000D);
        } catch (Exception e) {
            System.out.println("转账失败");
        }
    }

}