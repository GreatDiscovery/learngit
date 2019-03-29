package com.example.spring.spring_tx.service;

import com.example.spring.spring_tx.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gavin
 * @date 2018/12/12 14:01
 */
@Transactional(rollbackFor = Exception.class, transactionManager = "txManager")
@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public void transfer(final String out, final String in, final Double money) throws Exception {
        int count = accountDao.updateForOut(out, money);
        accountDao.updateForIn(in, money);
        if (count == 0) {
            throw new Exception("转账失败");
        }
    }
}
