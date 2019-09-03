package com.example.spring.spring_aop;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author gavin
 * @date 2018/12/6 15:41
 */
@Service
public class UserService {
    public UserService() {
    }

    public void add() {
        System.out.println("UserService add()");
    }

    public boolean delete() {
        System.out.println("UserService delete()");
        return true;
    }

    public void edit() {
        System.out.println("UserService edit()");
        int i = 5 / 0;
    }
}
