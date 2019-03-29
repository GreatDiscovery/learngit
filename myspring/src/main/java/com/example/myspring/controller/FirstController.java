package com.example.myspring.controller;

import com.example.myspring.annotation.MyAutowired;
import com.example.myspring.annotation.MyController;
import com.example.myspring.annotation.MyRequestMapping;
import com.example.myspring.annotation.MyService;

/**
 * @author gavin
 * @date 2019/3/27 12:18
 */

@MyController
//@MyRequestMapping("/")
public class FirstController {
    @MyAutowired
    private MyService myService;

    @MyRequestMapping("/hello")
    public String hello() {
        return "hello, gavin";
    }
}
