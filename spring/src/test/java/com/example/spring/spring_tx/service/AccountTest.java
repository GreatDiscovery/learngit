package com.example.spring.spring_tx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
        } finally {
            System.out.println("finally成功");
        }
    }

    @Test
    public void testList() {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        lists.add(new ArrayList<>(list));
        System.out.println(lists);
    }
}
