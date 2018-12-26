package com.example.spring.spring_tx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

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